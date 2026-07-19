package com.user.yesbank.service.imp;


import com.user.yesbank.dto.UserEntityDTO;
import com.user.yesbank.dto.UserProfleDTO;
import com.user.yesbank.entity.UserEntity;
import com.user.yesbank.entity.UserProfile;
import com.user.yesbank.exception.CustomerAlreadyExistsException;
import com.user.yesbank.exception.ResourceNotFoundException;
import com.user.yesbank.mapper.UserEntityMapper;
import com.user.yesbank.mapper.UserProfileMapper;
import com.user.yesbank.repository.UserEntityRepository;
import com.user.yesbank.repository.UserProfileRepository;
import com.user.yesbank.service.UserProfilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfilesService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public void createAccount(UserEntityDTO dto) {

        Optional<UserEntity> optionalUser = userEntityRepository.findByBankId(dto.getBankId());

        if (optionalUser.isPresent()) {

            throw new CustomerAlreadyExistsException("User already exists with bankId " + dto.getBankId());
        }
        UserEntity userEntity = UserEntityMapper.mapToUserEntity(dto, new UserEntity());

        userEntity.setAccountLinked(Boolean.TRUE);

        UserEntity savedUser = userEntityRepository.save(userEntity);

        UserProfile profile = createNewAccount(savedUser, dto);

        userProfileRepository.save(profile);
    }

    private UserProfile createNewAccount(UserEntity userEntity, UserEntityDTO dto) {

        UserProfleDTO profileDTO = dto.getUserProfleDTO();

        UserProfile profile = new UserProfile();

        profile.setUserId(userEntity.getUserId());

        profile.setName(profileDTO.getName());

        profile.setEmail(profileDTO.getEmail());

        profile.setGender(profileDTO.getGender());

        profile.setMobileNumber(profileDTO.getMobileNumber());

        profile.setStreet(profileDTO.getStreet());

        profile.setCity(profileDTO.getCity());

        profile.setPincode(profileDTO.getPincode());

        return profile;
    }

    //    @Override
//    public UserEntityDTO fetchAccount(Long bankId) {
//        UserEntity userEntity = userEntityRepository.findByBankId(bankId).orElseThrow(
//                () -> new ResourceNotFoundException("UserEntity", "bankId", bankId)
//        );
//        UserProfile userProfile = userEntityRepository.findByBankId(userEntity.getBankId()).orElseThrow(
//                () -> new ResourceNotFoundException("UserProfile", "bankId", userEntity.getBankId().toString())
//        );
//        UserEntityDTO userEntityDTO = UserEntityMapper.mapToUserEntityDTO(userEntity, new UserEntityDTO());
//        userEntityDTO.setUserProfleDTO(UserProfileMapper.mapToUserProfileDTO(userProfile, new UserProfleDTO()));
//        return userEntityDTO;
//    }
    @Override
    public UserEntityDTO fetchAccount(Long bankId) {

        UserEntity userEntity = userEntityRepository.findByBankId(bankId).orElseThrow(() -> new ResourceNotFoundException("User not found with bankId : " + bankId));

        UserProfile userProfile = userProfileRepository.findByUserId(userEntity.getUserId()).orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        UserEntityDTO dto = UserEntityMapper.mapToUserEntityDTO(userEntity, new UserEntityDTO());

        dto.setUserProfleDTO(UserProfileMapper.mapToUserProfileDTO(userProfile, new UserProfleDTO()));

        return dto;
    }

//    @Override
//    public boolean updateAccount(UserEntityDTO userEntityDTO) {
//        boolean isUpdated = false;
//        UserProfleDTO userProfleDTO = userEntityDTO.getUserProfleDTO();
//        if (userProfleDTO != null) {
//            UserProfile userProfile = userProfilesService.findByBankId(userProfleDTO.getAccountNumber()).orElseThrow(
//                    () -> new ResourceNotFoundException("Account", "AccountNumber", userProfleDTO.getAccountNumber().toString())
//            );
//            UserProfileMapper.mapToUserProfile(userProfleDTO, userProfile);
//            userProfile = userProfilesService.save(userProfile);
//
//            Long userId = userProfile.getBankId();
//            UserEntity userEntity = userEntityRepository.findById(bankId).orElseThrow(
//                    () -> new ResourceNotFoundException("Customer", "CustomerID", userId.toString())
//            );
//            UserEntityMapper.mapToUserEntity(userEntityDTO, userEntity);
//            userEntityRepository.save(userEntity);
//            isUpdated = true;
//        }
//        return isUpdated;

    @Override
    public boolean updateAccount(UserEntityDTO userEntityDTO) {

        UserEntity userEntity = userEntityRepository.findByBankId(userEntityDTO.getBankId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        UserProfile userProfile = userProfileRepository.findByUserId(userEntity.getUserId()).orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        UserEntityMapper.mapToUserEntity(userEntityDTO, userEntity);

        userEntityRepository.save(userEntity);

        UserProfileMapper.mapToUserProfile(userEntityDTO.getUserProfleDTO(), userProfile);

        userProfileRepository.save(userProfile);

        return true;
    }

    //    @Override
//    public boolean deleteAccount(Long bankId) {
//        UserEntity userEntity = userEntityRepository.findByBankId(bankId).orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", bankId));
//        userProfilesService.deleteByUserId(userEntity.getUserId());
//        userEntityRepository.deleteById(userEntity.getUserId());
//        return true;
//    }
    @Override
    public boolean deleteAccount(Long bankId) {

        UserEntity userEntity = userEntityRepository.findByBankId(bankId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userProfileRepository.deleteByUserId(userEntity.getUserId());

        userEntityRepository.deleteById(userEntity.getUserId());

        return true;
    }
}



