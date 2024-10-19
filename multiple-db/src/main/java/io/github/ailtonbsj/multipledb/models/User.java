package io.github.ailtonbsj.multipledb.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "USERS", schema="MULTIDB")
@Data
public class User {
    
    @EmbeddedId
    UserPK id;

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
