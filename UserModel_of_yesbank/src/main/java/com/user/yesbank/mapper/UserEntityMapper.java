package com.user.yesbank.mapper;

import com.user.yesbank.dto.UserEntityDTO;
import com.user.yesbank.dto.BankCustomerDetailsDTO;

import com.user.yesbank.entity.UserEntity;

public class UserEntityMapper {

    public static UserEntityDTO mapToUserEntityDTO(UserEntity userEntity, UserEntityDTO userEntitydto) {
        userEntitydto.setStatus(userEntity.getStatus());
        userEntitydto.setBankId(userEntity.getBankId());
        userEntitydto.setAccountLinked(userEntity.getAccountLinked());
        userEntitydto.setRole(userEntity.getRole());
        return userEntitydto;
    }
    public static BankCustomerDetailsDTO mapToBankCustomerDetailsDTO(UserEntity userEntity, BankCustomerDetailsDTO userEntityDetailsDTO) {
        userEntityDetailsDTO.setStatus(userEntity.getStatus());
        userEntityDetailsDTO.setBankId(userEntity.getBankId());
        userEntityDetailsDTO.setAccountLinked(userEntity.getAccountLinked());
        userEntityDetailsDTO.setRole(userEntity.getRole());
        return userEntityDetailsDTO;
    }
    public static UserEntity mapToUserEntity(UserEntityDTO userEntityDTO, UserEntity userEntity){
        userEntity.setStatus(userEntityDTO.getStatus());
        userEntity.setBankId(userEntityDTO.getBankId());
        userEntity.setAccountLinked(userEntityDTO.getAccountLinked());
        userEntity.setRole(userEntityDTO.getRole());
     return userEntity;   
    }
}
