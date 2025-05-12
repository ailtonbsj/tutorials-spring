package br.ailtonbsj.studies.oracledb_cp.domain.key;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPK implements Serializable {

    private String username;
    private LocalDateTime createdAt;
    
}
