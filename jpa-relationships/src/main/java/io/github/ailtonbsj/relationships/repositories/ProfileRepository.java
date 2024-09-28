package io.github.ailtonbsj.relationships.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.ailtonbsj.relationships.models.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
