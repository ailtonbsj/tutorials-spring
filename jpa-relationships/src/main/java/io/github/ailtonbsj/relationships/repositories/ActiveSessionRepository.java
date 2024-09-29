package io.github.ailtonbsj.relationships.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.ailtonbsj.relationships.models.ActiveSession;

public interface ActiveSessionRepository extends JpaRepository<ActiveSession, Long> {
}
