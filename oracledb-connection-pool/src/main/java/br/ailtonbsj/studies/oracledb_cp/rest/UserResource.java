package br.ailtonbsj.studies.oracledb_cp.rest;

import br.ailtonbsj.studies.oracledb_cp.domain.User;
import br.ailtonbsj.studies.oracledb_cp.domain.key.UserPK;
import br.ailtonbsj.studies.oracledb_cp.repository.UserRepository;
import br.ailtonbsj.studies.oracledb_cp.util.Util;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "User")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserResource {

    private final UserRepository repository;

    @GetMapping("filter")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Filtra registros por exemplo")
    public Page<User> filter(
            User sample,
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
    public List<User> index() {
        return repository.findAll();
    }

    @PostMapping
    @Operation(summary = "Cria novo registro")
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        var key = new UserPK(user.getUsername(), user.getCreatedAt());
        var res = repository.findById(key);
        if(res.isPresent())
            throw new DataIntegrityViolationException("Já existem registros com mesmos valores na base.");
        var saved = repository.save(user);
        return Optional.ofNullable(saved)
            .map(created -> new ResponseEntity<User>(created, HttpStatus.CREATED))
            .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("show")
    @Operation(summary = "Obtem um registro pelo ID")
    public ResponseEntity<User> show(UserPK id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    @Operation(summary = "Atualiza um registro por completo")
    public ResponseEntity<User> update(UserPK id, @Valid @RequestBody User user) {
        user.setUsername(id.getUsername());
        user.setCreatedAt(id.getCreatedAt());

        return repository.findById(id)
                .stream()
                .map(ent -> user)
                .map(repository::save)
                .findAny()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    @Operation(summary = "Remove um registro pelo ID")
    public ResponseEntity<Void> destroy(UserPK id) {
        if (repository.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
