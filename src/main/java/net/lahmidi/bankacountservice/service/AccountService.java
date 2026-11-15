package net.lahmidi.bankacountservice.service;

import net.lahmidi.bankacountservice.dto.BankAccountRequestDTO;
import net.lahmidi.bankacountservice.dto.BankAccountResponseDTO;
import net.lahmidi.bankacountservice.entities.BankAccount;
import net.lahmidi.bankacountservice.enums.AccountType;

public interface AccountService {
    BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
}
