package io.github.ailtonbsj.relationships.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.ailtonbsj.relationships.dtos.OrganizationalUnitDTO;
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

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@Valid @RequestBody OrganizationalUnitDTO dto) {
        service.create(mapper.toModel(dto));
    }

}
