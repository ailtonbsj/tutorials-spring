package io.github.ailtonbsj.relationships.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.ailtonbsj.relationships.dtos.OrganizationalUnitDTO;
import io.github.ailtonbsj.relationships.services.OrganizationalUnitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/organizationalunits")
@RequiredArgsConstructor
public class OrganizationalUnitController {

    private final OrganizationalUnitService service;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<OrganizationalUnitDTO> index() {
        return service.index();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public OrganizationalUnitDTO create(@Valid @RequestBody OrganizationalUnitDTO dto) {
        return service.create(dto);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrganizationalUnitDTO> show(@PathVariable Long id) {
        return service.show(id)
                .map(dto -> ResponseEntity.ok(dto))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<OrganizationalUnitDTO> update(@PathVariable Long id,
            @Valid @RequestBody OrganizationalUnitDTO dto) {
        return service.update(id, dto)
                .map(ent -> ResponseEntity.ok(ent))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> destroy(@PathVariable Long id) {
        if(!service.destroy(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

}
