package com.account.yesbank.service.imp;

import com.account.yesbank.dto.BankAccountDTO;
import com.account.yesbank.entity.BankAccountEntity;
import com.account.yesbank.enums.AccountStatus;
import com.account.yesbank.exception.AccountException;
import com.account.yesbank.mapper.AccountMapper;
import com.account.yesbank.repository.BankAccountRepository;
import com.account.yesbank.service.BankAccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.account.yesbank.mapper.AccountMapper.generateAccountNumber;


@Service
public class BankAccountServiceImp implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountServiceImp(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public BankAccountDTO createAccount(BankAccountDTO dto) {

        BankAccountEntity entity = new BankAccountEntity();

        // Map basic fields
        AccountMapper.mapToBankAccountEntity(entity, dto);

        // ✅ Dynamic Balance
        if (dto.getBalance() != null) {
            entity.setBalance(dto.getBalance());
        } else {
            entity.setBalance(BigDecimal.ZERO);
        }

        // ✅ Dynamic Status
        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        } else {
            entity.setStatus(AccountStatus.OPEN);
        }
        // Generate account number
        entity.setAccountNumber(generateAccountNumber());

        // Ensure unique account number
        if (bankAccountRepository.existsByAccountNumber(entity.getAccountNumber())) {
            throw new AccountException("Account number already exists");
        }

        BankAccountEntity saved = bankAccountRepository.save(entity);

        return AccountMapper.mapToBankAccount(new BankAccountDTO(), saved);
    }

    @Override
    public BankAccountDTO getByBankId(Long bankId) {

        BankAccountEntity entity = bankAccountRepository.findByBankId(bankId).orElseThrow(() -> new AccountException("Account not found"));

        return AccountMapper.mapToBankAccount(new BankAccountDTO(), entity);
    }

    @Override
    public List<BankAccountDTO> getAll() {

        return bankAccountRepository.findAll().stream().map(entity -> AccountMapper.mapToBankAccount(new BankAccountDTO(), entity)).collect(Collectors.toList());
    }

    @Override
    public BankAccountDTO updateAccount(int id, BankAccountDTO dto) {

        BankAccountEntity entity = bankAccountRepository.findById(id).orElseThrow(() -> new AccountException("Account not found"));

        // Do not change account number & balance
        entity.setIfscCode(dto.getIfscCode());
        entity.setType(dto.getType());

        return AccountMapper.mapToBankAccount(new BankAccountDTO(), bankAccountRepository.save(entity));
    }

    @Override
    public BankAccountDTO updateStatus(int id, String status) {

        BankAccountEntity entity = bankAccountRepository.findById(id).orElseThrow(() -> new AccountException("Account not found"));

        try {
            entity.setStatus(AccountStatus.valueOf(status.toUpperCase()));
        } catch (Exception e) {
            throw new AccountException("Invalid account status");
        }

        return AccountMapper.mapToBankAccount(new BankAccountDTO(), bankAccountRepository.save(entity));
    }

    @Override
    public void deleteAccount(int id) {

        BankAccountEntity entity = bankAccountRepository.findById(id).orElseThrow(() -> new AccountException("Account not found"));

        // Soft delete
        entity.setStatus(AccountStatus.CLOSED);

        bankAccountRepository.save(entity);
    }
}