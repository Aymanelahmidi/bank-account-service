package net.lahmidi.bankacountservice.service;

import net.lahmidi.bankacountservice.dto.BankAccountRequestDTO;
import net.lahmidi.bankacountservice.dto.BankAccountResponseDTO;
import net.lahmidi.bankacountservice.entities.BankAccount;
import net.lahmidi.bankacountservice.mappers.AccountMapper;
import net.lahmidi.bankacountservice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

import static graphql.introspection.IntrospectionQueryBuilder.build;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {

        BankAccount bankAccount= BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();
        BankAccount saveBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(saveBankAccount);
        return bankAccountResponseDTO;
    }
}
