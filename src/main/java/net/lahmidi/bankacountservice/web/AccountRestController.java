package net.lahmidi.bankacountservice.web;

import net.lahmidi.bankacountservice.dto.BankAccountRequestDTO;
import net.lahmidi.bankacountservice.dto.BankAccountResponseDTO;
import net.lahmidi.bankacountservice.entities.BankAccount;
import net.lahmidi.bankacountservice.mappers.AccountMapper;
import net.lahmidi.bankacountservice.repositories.BankAccountRepository;
import net.lahmidi.bankacountservice.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class AccountRestController {

    private final BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;

    public AccountRestController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }
    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAccountRepository.findAll();
    }
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format("Account %s not found", id)));
    }
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO){
        return accountService.addAccount(requestDTO);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id,@RequestBody BankAccount bankAccount){
        BankAccount account=bankAccountRepository.findById(id).orElseThrow();
        if (bankAccount.getBalance() !=null) account.setBalance(bankAccount.getBalance());
        if (bankAccount.getCreatedAt() !=null) account.setCreatedAt(new Date());
        if (bankAccount.getType() !=null) account.setType(bankAccount.getType());
        if (bankAccount.getCurrency() !=null) account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(account);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id){
        bankAccountRepository.deleteById(id);
    }
}
