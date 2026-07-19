package com.user.yesbank.service.imp;

import com.user.yesbank.controller.BankCustomerDetailsController;
import com.user.yesbank.dto.*;
import com.user.yesbank.entity.UserEntity;
import com.user.yesbank.entity.UserProfile;
import com.user.yesbank.exception.ResourceNotFoundException;
import com.user.yesbank.mapper.UserEntityMapper;
import com.user.yesbank.mapper.UserProfileMapper;
import com.user.yesbank.repository.UserEntityRepository;
import com.user.yesbank.repository.UserProfileRepository;
import com.user.yesbank.service.BankCustomerDetailsService;
import com.user.yesbank.service.client.AuthFeignClient;
import com.user.yesbank.service.client.BankAccountsFeignClient;
import com.user.yesbank.service.client.BanksFeignClient;
import com.user.yesbank.service.client.CardsFeignClint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankCustomerDtailsServiceImp implements BankCustomerDetailsService {


    private static final Logger logger = LoggerFactory.getLogger(BankCustomerDtailsServiceImp.class);

    @Autowired
    private AuthFeignClient authFeignClient;
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private BanksFeignClient banksFeignClient;
    @Autowired
    private BankAccountsFeignClient bankAccountsFeignClient;
    @Autowired
    private CardsFeignClint cardsFeignClint;


    @Override
    public BankCustomerDetailsDTO fetchBankCustomerDetails(Long bankId, String correlationId) {

        UserEntity userEntity = userEntityRepository.findByBankId(bankId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        UserProfile userProfile = userProfileRepository.findByUserId(userEntity.getUserId()).orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        BankCustomerDetailsDTO dto = UserEntityMapper.mapToBankCustomerDetailsDTO(userEntity, new BankCustomerDetailsDTO());

        dto.setUserProfleDTO(UserProfileMapper.mapToUserProfileDTO(userProfile, new UserProfleDTO()));

//        dto.setBankEnityDTO(banksFeignClient.fetchBankDetails(correlationId, bankId).getBody());
        try {
            dto.setBankEnityDTO(banksFeignClient.fetchBankDetails(correlationId, bankId).getBody());
        } catch (Exception ex) {
            logger.error("Bank service failed", ex);
        }

        //   dto.setCardsDTO(cardsFeignClint.fetchCardsDetails(correlationId, bankId).getBody());
        try {
            dto.setCardsDTO(cardsFeignClint.fetchCardsDetails(correlationId, bankId).getBody());
        } catch (Exception ex) {
            logger.error("Bank cardd service failed", ex);
        }
        // dto.setBankAccountDTO(bankAccountsFeignClient.fetchBankAccountDetails(correlationId, bankId).getBody());
        try {
            dto.setBankAccountDTO(bankAccountsFeignClient.fetchBankAccountDetails(correlationId, bankId).getBody());
        } catch (Exception ex) {
            logger.error("BankAccount service failed", ex);
        }
//        try{
//            dto.setRegisterRequestDTO(authFeignClient.registerUser(new RegisterRequestDTO()).getBody());
//        }catch(Exception ex){
//            logger.error("authuser service failed", ex);
//        }
        return dto;
    }
}
