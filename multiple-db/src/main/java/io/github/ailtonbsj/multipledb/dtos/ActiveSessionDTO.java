package io.github.ailtonbsj.multipledb.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ActiveSessionDTO {
    String id;

    @NotBlank
    String device;
    
    @NotBlank
    String agent;
    
    @NotNull
    LocalDateTime signedIn;

    @NotNull
    String userId;

    @NotNull
    LocalDate userCreatedAt;
}
