package io.github.ailtonbsj.relationships.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.ailtonbsj.relationships.models.OrganizationalUnit;
import io.github.ailtonbsj.relationships.repositories.OrganizationalUnitRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrganizationalUnitService {

    private final OrganizationalUnitRepository repository;

    public OrganizationalUnit create(OrganizationalUnit ou){
        return repository.save(ou);
    }

    public Optional<OrganizationalUnit> show(Long id) {
        return repository.findById(id);
    }
}
