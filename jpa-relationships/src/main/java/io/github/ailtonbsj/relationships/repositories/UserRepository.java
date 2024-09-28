package io.github.ailtonbsj.relationships.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.ailtonbsj.relationships.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
