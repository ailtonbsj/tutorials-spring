package br.ailtonbsj.studies.oracledb_cp.domain;

import br.ailtonbsj.studies.oracledb_cp.domain.key.UserPK;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@IdClass(UserPK.class)
@Table(name = "users", schema = "studyuser")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Id
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_active", nullable = false)
    private Integer isActive;

    @Column(name = "department_id", nullable = true)
    private Long departmentId;

    @Column(name = "profile_id", nullable = true)
    private Long profileId;

}
