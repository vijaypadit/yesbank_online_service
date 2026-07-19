package com.account.yesbank.utility;

import java.util.Random;

public class AccountNumberGenerator {
    public static Long generate(Long userId) {
        Random random = new Random();
        int randomNum = 100000000 + random.nextInt(900000000);
        return Long.valueOf(userId + "" + randomNum);
    }
}