package io.github.ailtonbsj.multipledb.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@IdClass(UserPK.class)
@Table(name = "USERS", schema="MULTIDB")
@Data
public class User {
    
    @Id
    String username;

    @Id
    LocalDate createdAt;

    String password;

    Boolean isActive;

    Integer profileId;

    Integer departmentId;

    // List<Role> roles;

    // @OneToMany(fetch = FetchType.LAZY)
    // @JoinColumns({
    //     @JoinColumn(name = "username"),
    //     @JoinColumn(name = "created_at")
    // })
    // List<ActiveSession> activeSessions;

}
