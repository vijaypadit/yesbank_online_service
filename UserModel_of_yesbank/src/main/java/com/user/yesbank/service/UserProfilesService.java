package com.user.yesbank.service;

import com.user.yesbank.dto.UserEntityDTO;

public interface UserProfilesService {

    void createAccount(UserEntityDTO userEntityDTO);

    UserEntityDTO fetchAccount(Long bankId);

    boolean updateAccount(UserEntityDTO userEntityDTO);

    boolean deleteAccount(Long bankId);
}