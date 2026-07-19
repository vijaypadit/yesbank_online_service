package com.user.yesbank.dto;

import com.user.yesbank.enums.UserRole;
import com.user.yesbank.enums.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityDTO {

    private UserStatus status;

    private Long bankId; // reference only

    private Boolean accountLinked;

    private UserRole role;

    private UserProfleDTO userProfleDTO;
}