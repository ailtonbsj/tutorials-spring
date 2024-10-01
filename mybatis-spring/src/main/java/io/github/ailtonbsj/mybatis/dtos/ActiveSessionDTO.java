package io.github.ailtonbsj.mybatis.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActiveSessionDTO {
    Long id;

    @NotBlank
    String device;
    
    @NotBlank
    String agent;
    
    @NotNull
    LocalDateTime signedIn;

    @NotNull
    Long userId;
}
