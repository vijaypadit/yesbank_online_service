package com.account.yesbank.repository;


import com.account.yesbank.dto.BankAccountDTO;
import com.account.yesbank.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Integer> {

    Optional<BankAccountEntity> findByAccountNumber(String accountNumber);

    boolean existsByAccountNumber(String accountNumber);

    Optional<BankAccountEntity> findByBankId(Long bankId);

    //public BankAccountDTO deposit(int id, BigDecimal amount);
}
