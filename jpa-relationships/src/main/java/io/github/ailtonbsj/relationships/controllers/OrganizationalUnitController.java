package io.github.ailtonbsj.relationships.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.ailtonbsj.relationships.dtos.OrganizationalUnitDTO;
import io.github.ailtonbsj.relationships.dtos.OrganizationalUnitSimpleDTO;
import io.github.ailtonbsj.relationships.mappers.OrganizationalUnitMapper;
import io.github.ailtonbsj.relationships.services.OrganizationalUnitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/organizationalunits")
@RequiredArgsConstructor
public class OrganizationalUnitController {

    private final OrganizationalUnitService service;
    private final OrganizationalUnitMapper mapper;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<OrganizationalUnitDTO> index() {
        return mapper.toDto(service.index());
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@Valid @RequestBody OrganizationalUnitSimpleDTO dto) {
        service.create(mapper.toModel(dto));
    }

    @GetMapping("{id}")
    public ResponseEntity<OrganizationalUnitDTO> show(@PathVariable Long id){
        return service.show(id)
            .map(mapper::toDto)
            .map(dto -> ResponseEntity.ok(dto))
            .orElse(ResponseEntity.notFound().build());
    }

}
