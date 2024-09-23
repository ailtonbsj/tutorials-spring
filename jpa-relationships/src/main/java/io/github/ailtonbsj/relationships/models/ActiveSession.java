package io.github.ailtonbsj.relationships.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ActiveSession {
    @Id
    Long id;

    String device;
    
    String agent;
    
    LocalDateTime signedIn;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
