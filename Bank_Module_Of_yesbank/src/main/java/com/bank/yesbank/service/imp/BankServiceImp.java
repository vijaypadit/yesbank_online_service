package com.bank.yesbank.service.imp;

import com.bank.yesbank.dto.BankEntityDTO;
import com.bank.yesbank.entity.BankEntity;
import com.bank.yesbank.enums.BankStatus;
import com.bank.yesbank.exception.BankException;
import com.bank.yesbank.mapper.BankEntityMapper;
import com.bank.yesbank.repository.BankRepository;
import com.bank.yesbank.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankServiceImp implements BankService {

    private final BankRepository repository;

    public BankServiceImp(BankRepository repository) {
        this.repository = repository;
    }
    private static final Logger log = LoggerFactory.getLogger(BankServiceImp.class);

    @Override
    public BankEntityDTO createBank(BankEntityDTO dto) {

        // ✅ Business Rule 1: Unique Code
        if (repository.existsByCode(dto.getCode())) {
            throw new BankException("Bank code already exists");
        }

        BankEntity entity = new BankEntity();

        // ✅ Mapping
        BankEntityMapper.toEntity(dto, entity);

        // ✅ Business Rule 2: Default Status
        if (entity.getStatus() == null) {
            entity.setStatus(BankStatus.ACTIVE);
        }

        BankEntity saved = repository.save(entity);

        return BankEntityMapper.mapToBankEntityDTO(saved, new BankEntityDTO());
    }

    @Override
    public BankEntityDTO getBankById(Long bankId) {

        BankEntity entity = repository.findById(bankId)
                .orElseThrow(() -> new BankException("Bank not found"));

        return BankEntityMapper.mapToBankEntityDTO(entity, new BankEntityDTO());
    }

    @Override
    public List<BankEntityDTO> getAllBanks() {

        return repository.findAll()
                .stream()
                .map(bank -> BankEntityMapper.mapToBankEntityDTO(bank, new BankEntityDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public BankEntityDTO updateBank(Long bankId, BankEntityDTO dto) {

        BankEntity existing = repository.findById(bankId)
                .orElseThrow(() -> new BankException("Bank not found"));

        // ✅ Business Rule: Code cannot be changed
        if (!existing.getCode().equals(dto.getCode())) {
            throw new BankException("Bank code cannot be changed");
        }

        BankEntityMapper.toEntity(dto, existing);

        BankEntity updated = repository.save(existing);

        return BankEntityMapper.mapToBankEntityDTO(updated, new BankEntityDTO());
    }

    @Override
    public void deleteBank(Long bankId) {

        BankEntity entity = repository.findById(bankId)
                .orElseThrow(() -> new BankException("Bank not found"));

        // ✅ Enterprise Rule: Soft delete instead of hard delete
        entity.setStatus(BankStatus.INACTIVE);

        repository.save(entity);
    }
}
