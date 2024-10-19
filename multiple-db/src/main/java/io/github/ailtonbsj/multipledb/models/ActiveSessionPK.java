package io.github.ailtonbsj.multipledb.models;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveSessionPK implements Serializable {

    String userId;
    LocalDate userCreatedAt;
    String device;

}
