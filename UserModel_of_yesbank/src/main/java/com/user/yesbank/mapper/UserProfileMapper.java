package com.user.yesbank.mapper;


import com.user.yesbank.dto.UserProfleDTO;
import com.user.yesbank.entity.UserProfile;

public class UserProfileMapper {

    public static UserProfleDTO mapToUserProfileDTO(UserProfile userProfle, UserProfleDTO userProfleDTO) {
        userProfleDTO.setName(userProfle.getName());
        userProfleDTO.setEmail(userProfle.getEmail());
        userProfleDTO.setGender(userProfle.getGender());
        userProfleDTO.setMobileNumber(userProfle.getMobileNumber());
        userProfleDTO.setStreet(userProfle.getStreet());
        userProfleDTO.setCity(userProfle.getCity());
        userProfleDTO.setPincode(userProfle.getPincode());
        return userProfleDTO;
    }


    public static UserProfile mapToUserProfile(UserProfleDTO userProfleDTO, UserProfile userProfle){
        userProfle.setName(userProfleDTO.getName());

        userProfle.setEmail(userProfleDTO.getEmail());
        userProfle.setGender(userProfleDTO.getGender());
        userProfle.setMobileNumber(userProfleDTO.getMobileNumber());
        userProfle.setStreet(userProfleDTO.getStreet());
        userProfle.setCity(userProfleDTO.getCity());
        userProfle.setPincode(userProfleDTO.getPincode());
        return userProfle;
    }
}
