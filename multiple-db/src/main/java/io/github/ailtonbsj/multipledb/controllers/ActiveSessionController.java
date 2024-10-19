package io.github.ailtonbsj.multipledb.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.ailtonbsj.multipledb.dtos.ActiveSessionDTO;
import io.github.ailtonbsj.multipledb.models.ActiveSessionPK;
import io.github.ailtonbsj.multipledb.services.ActiveSessionService;
import io.github.ailtonbsj.multipledb.utils.Utils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/activesessions")
@RequiredArgsConstructor
public class ActiveSessionController {

    private final ActiveSessionService service;

    @PostMapping("filter")
    @ResponseStatus(value = HttpStatus.OK)
    public Page<ActiveSessionDTO> filter(
            @RequestBody ActiveSessionDTO example,
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize,
            @RequestParam String[] directions,
            @RequestParam String[] sortProps) {
        Sort sort = Utils.directionPropsToOrders(directions, sortProps);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return service.filter(example, pageable);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<ActiveSessionDTO> index() {
        return service.index();
    }

    @PostMapping
    public ResponseEntity<ActiveSessionDTO> create(@Valid @RequestBody ActiveSessionDTO dto) {
        return Optional.ofNullable(service.create(dto))
                .map(created -> new ResponseEntity<ActiveSessionDTO>(created, HttpStatus.CREATED))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<ActiveSessionDTO> show(@PathVariable String id) {
        var key = Utils.stringToId(id, ActiveSessionPK.class);
        return service.show(key)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<ActiveSessionDTO> update(@PathVariable String id, @Valid @RequestBody ActiveSessionDTO dto) {
        var key = Utils.stringToId(id, ActiveSessionPK.class);
        return service.update(key, dto)
                .map(ent -> ResponseEntity.ok(ent))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> destroy(@PathVariable String id) {
        var key = Utils.stringToId(id, ActiveSessionPK.class);
        if (!service.destroy(key))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

}
