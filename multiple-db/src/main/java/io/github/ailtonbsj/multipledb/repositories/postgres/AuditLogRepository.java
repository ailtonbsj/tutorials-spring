package io.github.ailtonbsj.multipledb.repositories.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.ailtonbsj.multipledb.models.postgres.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

}
