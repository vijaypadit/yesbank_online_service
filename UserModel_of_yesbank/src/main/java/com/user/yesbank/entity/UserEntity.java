package com.user.yesbank.entity;


import com.user.yesbank.enums.UserRole;
import com.user.yesbank.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private Long bankId; // reference only
    private Boolean accountLinked;
    @Enumerated(EnumType.STRING)
    private UserRole role;

}
