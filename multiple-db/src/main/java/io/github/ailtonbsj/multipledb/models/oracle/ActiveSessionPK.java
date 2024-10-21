package io.github.ailtonbsj.multipledb.models.oracle;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveSessionPK implements Serializable {

    String userId;
    LocalDate userCreatedAt;
    String device;

}
