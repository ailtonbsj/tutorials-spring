package io.github.ailtonbsj.multipledb.models.postgres;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "audit_logs", schema = "public")
@Data
public class AuditLog {

    @Id
    Long id;

    @Column(nullable = false)
    String userId;

    @Column(nullable = false)
    String action;

    @Column(nullable = false)
    LocalDateTime timestamp;

}
