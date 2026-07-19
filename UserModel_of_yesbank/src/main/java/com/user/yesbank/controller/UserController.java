package com.user.yesbank.controller;

import com.user.yesbank.constant.AccountsConstants;
import com.user.yesbank.dto.ResponseDto;
import com.user.yesbank.dto.UserEntityDTO;
import com.user.yesbank.service.UserProfilesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final UserProfilesService userProfilesService;
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody UserEntityDTO userEntityDTO) {

        userProfilesService.createAccount(userEntityDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<UserEntityDTO> fetchAccountDetails(@RequestHeader("yesbank-correlation-id") String correlationId, @RequestParam Long bankId) {

        UserEntityDTO userEntityDTO = userProfilesService.fetchAccount(bankId);
        return ResponseEntity.status(HttpStatus.OK).body(userEntityDTO);
//        return ResponseEntity.ok(userEntityDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody UserEntityDTO userEntityDTO) {

        boolean isUpdated = userProfilesService.updateAccount(userEntityDTO);

        if (isUpdated) {

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam Long bankId) {

        boolean isDeleted = userProfilesService.deleteAccount(bankId);

        if (isDeleted) {

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }
}