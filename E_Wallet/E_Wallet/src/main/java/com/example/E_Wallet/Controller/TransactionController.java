package com.example.E_Wallet.Controller;

import com.example.E_Wallet.DTO.TransactionsDTO;
import com.example.E_Wallet.DTO.UserDTO;
import com.example.E_Wallet.Entity.User;
import com.example.E_Wallet.Repository.UserRepository;
import com.example.E_Wallet.Service.TransactionsService;
import com.example.E_Wallet.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final UserService userService;
    private final TransactionsService transactionsService;

    @PostMapping("/withdraw")
    public UserDTO withdraw (@RequestParam UUID id,@RequestParam double Amount){
        return userService.WithdrawMoney(id,Amount);
    }

    @PostMapping("/Deposit")
    public UserDTO Deposit(@RequestParam UUID id, @RequestParam double Amount){
        return userService.Deposit(id,Amount);
    }
    @PostMapping("/Transfer")
    public UserDTO Transfer(@RequestParam UUID withdrawId,@RequestParam UUID depositId,@RequestParam double Amount){
        return userService.TransferMoney(withdrawId,depositId,Amount);
    }

    @GetMapping("/GetTransactions")
    public List<TransactionsDTO> GetTransctions(@RequestParam UUID id){
        return transactionsService.findByUser_UserId(id);
    }
}
