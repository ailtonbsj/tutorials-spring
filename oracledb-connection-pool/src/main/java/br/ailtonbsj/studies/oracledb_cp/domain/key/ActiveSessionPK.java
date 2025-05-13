package br.ailtonbsj.studies.oracledb_cp.domain.key;

import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActiveSessionPK implements Serializable {

    private String userUsername;
    private LocalDateTime userCreatedAt;
    private String device;
    
}
