package com.bank.yesbank.repository;

import com.bank.yesbank.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, Long> {

    Optional<BankEntity> findByCode(String code);

    boolean existsByCode(String code);
}