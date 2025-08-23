package com.example.E_Wallet.Controller;

import com.example.E_Wallet.DTO.UserDTO;
import com.example.E_Wallet.Entity.User;
import com.example.E_Wallet.Repository.UserRepository;
import com.example.E_Wallet.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final UserService userService;

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
}
