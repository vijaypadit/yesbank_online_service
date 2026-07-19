package com.user.yesbank.dto;

import com.user.yesbank.enums.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfleDTO {
    private String name;

    private String email;

    private Gender gender;

    private String mobileNumber;

    private String street;

    private String city;

    private String pincode;

}
