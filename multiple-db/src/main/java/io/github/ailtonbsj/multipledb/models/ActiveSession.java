package io.github.ailtonbsj.multipledb.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@IdClass(ActiveSessionPK.class)
@Table(name = "ACTIVE_SESSION", schema = "MULTIDB")
@Data
public class ActiveSession {

    @Id
    @Column(name = "user_id")
    String userId;

    @Id
    @Column(name = "user_created_at")
    LocalDate userCreatedAt;
    
    @Id
    String device;

    String agent;

    LocalDateTime signedIn;

}
