package io.github.ailtonbsj.relationships.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.ailtonbsj.relationships.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
