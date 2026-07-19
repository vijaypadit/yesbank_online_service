package com.user.yesbank.entity;

import com.user.yesbank.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_profiles")
public class UserProfile extends BaseEntity {
    @Id
    private Long userId;
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String mobileNumber;
    private String street;
    private String city;
    private String pincode;
}
