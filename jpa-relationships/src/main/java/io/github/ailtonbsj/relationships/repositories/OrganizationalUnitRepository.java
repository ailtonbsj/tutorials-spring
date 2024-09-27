package io.github.ailtonbsj.relationships.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.ailtonbsj.relationships.models.OrganizationalUnit;
import jakarta.transaction.Transactional;

public interface OrganizationalUnitRepository extends JpaRepository<OrganizationalUnit, Long> {

    @Transactional @Modifying
    @Query(value = "delete from organizational_unit where id = :id", nativeQuery = true)
    void deleteByIdFaster(@Param("id") Long id);

}
