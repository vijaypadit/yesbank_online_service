package com.user.yesbank.dto;

import com.user.yesbank.enums.UserRole;
import com.user.yesbank.enums.UserStatus;
import lombok.Data;

@Data
public class BankCustomerDetailsDTO {

    private UserStatus status;

    private Long bankId; // reference only

    private Boolean accountLinked;

    private UserRole role;

    private UserProfleDTO userProfleDTO;

    private BankAccountDTO bankAccountDTO;

    private BankEnityDTO bankEnityDTO;

    private CardsDTO cardsDTO;

    private RegisterRequestDTO registerRequestDTO;

}