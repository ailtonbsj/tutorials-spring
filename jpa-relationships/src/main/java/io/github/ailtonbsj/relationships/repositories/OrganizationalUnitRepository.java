package io.github.ailtonbsj.relationships.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.ailtonbsj.relationships.models.OrganizationalUnit;

public interface OrganizationalUnitRepository extends JpaRepository<OrganizationalUnit, Long> {
}
