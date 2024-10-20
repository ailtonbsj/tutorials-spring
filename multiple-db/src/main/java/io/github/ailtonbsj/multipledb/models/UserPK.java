package io.github.ailtonbsj.multipledb.models;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPK  implements Serializable {

    String username;
    LocalDate createdAt;

}
