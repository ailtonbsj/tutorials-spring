package br.ailtonbsj.studies.oracledb_cp.domain;

import br.ailtonbsj.studies.oracledb_cp.domain.key.ActiveSessionPK;
import lombok.*;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@IdClass(ActiveSessionPK.class)
@Table(name = "active_sessions", schema = "studyuser")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActiveSession {

    @Id
    @Column(name = "user_username", nullable = false)
    private String userUsername;

    @Id
    @Column(name = "user_created_at", nullable = false)
    private LocalDateTime userCreatedAt;

    @Id
    @Column(name = "device", nullable = true)
    private String device;

    @Column(name = "agent", nullable = true)
    private String agent;

    @Column(name = "signed_in", nullable = true)
    private LocalDateTime signedIn;

}
