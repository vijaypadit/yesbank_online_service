package com.bank.yesbank.mapper;


import com.bank.yesbank.dto.BankEntityDTO;
import com.bank.yesbank.entity.BankEntity;
public class BankEntityMapper {

    public static BankEntity toEntity(BankEntityDTO bankEntityDTO, BankEntity bankEntity) {
        bankEntity.setName(bankEntityDTO.getName());
        bankEntity.setBankId(bankEntityDTO.getBankId());
        bankEntity.setCode(bankEntityDTO.getCode());
        bankEntity.setBranchAddress(bankEntityDTO.getBranchAddress());
        bankEntity.setPhoneNumber(bankEntityDTO.getPhoneNumber());
        bankEntity.setEmail(bankEntityDTO.getEmail());
        bankEntity.setWebsite(bankEntityDTO.getWebsite());
        bankEntity.setCountry(bankEntityDTO.getCountry());
        bankEntity.setCurrency(bankEntityDTO.getCurrency());
        bankEntity.setStatus(bankEntityDTO.getStatus()); // default
        return bankEntity;
    }

    public static BankEntityDTO mapToBankEntityDTO(BankEntity bankEntity, BankEntityDTO bankEntityDTO) {
        bankEntityDTO.setName(bankEntity.getName());
        bankEntityDTO.setBankId(bankEntity.getBankId());
        bankEntityDTO.setCode(bankEntity.getCode());
        bankEntityDTO.setBranchAddress(bankEntity.getBranchAddress());
        bankEntityDTO.setPhoneNumber(bankEntity.getPhoneNumber());
        bankEntityDTO.setEmail(bankEntity.getEmail());
        bankEntityDTO.setWebsite(bankEntity.getWebsite());
        bankEntityDTO.setCountry(bankEntity.getCountry());
        bankEntityDTO.setCurrency(bankEntity.getCurrency());
        bankEntityDTO.setStatus(bankEntity.getStatus()); // default
        return bankEntityDTO;
    }


}
