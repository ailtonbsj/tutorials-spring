package io.github.ailtonbsj.multipledb.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@IdClass(UserPK.class)
@Table(name = "USERS", schema="MULTIDB")
@Data
public class User {
    
    @Id
    @Column(name = "username")
    String username;

    @Id
    @Column(name = "created_at")
    LocalDate createdAt;

    String password;

    Boolean isActive;

    Integer profileId;

    Integer departmentId;

    // List<Role> roles;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "user_id", referencedColumnName = "username"),
        @JoinColumn(name = "user_created_at", referencedColumnName = "created_at")
    })
    List<ActiveSession> activeSessions;

}
