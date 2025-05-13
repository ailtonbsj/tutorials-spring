package br.ailtonbsj.studies.oracledb_cp.rest;

import br.ailtonbsj.studies.oracledb_cp.domain.ActiveSession;
import br.ailtonbsj.studies.oracledb_cp.repository.ActiveSessionRepository;
import br.ailtonbsj.studies.oracledb_cp.domain.key.ActiveSessionPK;
import br.ailtonbsj.studies.oracledb_cp.util.Util;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Tag(name = "Active Session")
@RestController
@RequestMapping("/active-sessions")
@RequiredArgsConstructor
public class ActiveSessionResource {

    private final ActiveSessionRepository repository;

    @GetMapping("filter")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Filtra registros por exemplo")
    public Page<ActiveSession> filter(
            ActiveSession sample,
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize,
            @RequestParam String[] directions,
            @RequestParam String[] sortProps) {
        Sort sort = Util.directionPropsToOrders(directions, sortProps);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        var matcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        var example = Example.of(sample, matcher);
        return repository.findAll(example, pageable);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Lista todos registros")
    public List<ActiveSession> index() {
        return repository.findAll();
    }

    @PostMapping
    @Operation(summary = "Cria novo registro")
    public ResponseEntity<ActiveSession> create(@Valid @RequestBody ActiveSession as) {
        var key = new ActiveSessionPK(as.getUserUsername(), as.getUserCreatedAt(), as.getDevice());
        var res = repository.findById(key);
        if(res.isPresent())
            throw new DataIntegrityViolationException("Já existem registros com mesmos valores na base.");
        var saved = repository.save(as);
        return Optional.ofNullable(saved)
            .map(created -> new ResponseEntity<ActiveSession>(created, HttpStatus.CREATED))
            .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("show")
    @Operation(summary = "Obtem um registro pelo ID")
    public ResponseEntity<ActiveSession> show(ActiveSessionPK id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    @Operation(summary = "Atualiza um registro por completo")
    public ResponseEntity<ActiveSession> update(ActiveSessionPK id,
                                                @Valid @RequestBody ActiveSession as) {
        as.setUserUsername(id.getUserUsername());
        as.setUserCreatedAt(id.getUserCreatedAt());
        as.setDevice(id.getDevice());

        return repository.findById(id)
                .stream()
                .map(ent -> as)
                .map(repository::save)
                .findAny()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    @Operation(summary = "Remove um registro pelo ID")
    public ResponseEntity<Void> destroy(ActiveSessionPK id) {
        if (repository.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
