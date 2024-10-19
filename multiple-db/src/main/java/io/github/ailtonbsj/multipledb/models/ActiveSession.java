package io.github.ailtonbsj.multipledb.models;

import java.time.LocalDateTime;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ACTIVE_SESSION", schema = "MULTIDB")
@Data
@NoArgsConstructor
public class ActiveSession {

    @EmbeddedId
    ActiveSessionPK id;

    String agent;

    LocalDateTime signedIn;

}
