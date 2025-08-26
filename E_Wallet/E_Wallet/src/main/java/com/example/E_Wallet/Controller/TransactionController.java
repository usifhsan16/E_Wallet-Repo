package com.example.E_Wallet.Controller;

import com.example.E_Wallet.DTO.TransactionsDTO;
import com.example.E_Wallet.DTO.UserDTO;
import com.example.E_Wallet.Entity.User;
import com.example.E_Wallet.Repository.UserRepository;
import com.example.E_Wallet.Service.TransactionsService;
import com.example.E_Wallet.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final UserService userService;
    private final TransactionsService transactionsService;

    @PostMapping("/withdraw")
    public ResponseEntity<UserDTO> withdraw (@RequestParam UUID id, @RequestParam double Amount){
        UserDTO user= userService.WithdrawMoney(id,Amount);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/Deposit")
    public ResponseEntity<UserDTO> Deposit(@RequestParam UUID id, @RequestParam double Amount){
        UserDTO userDTO=userService.Deposit(id,Amount);
        return ResponseEntity.ok(userDTO);
    }
    @PostMapping("/Transfer")
    public ResponseEntity<UserDTO> Transfer(@RequestParam UUID withdrawId,@RequestParam UUID depositId,@RequestParam double Amount){
        UserDTO userDTO= userService.TransferMoney(withdrawId,depositId,Amount);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/GetTransactions")
    public ResponseEntity<List<TransactionsDTO>> GetTransctions(@RequestParam UUID id){
        List<TransactionsDTO>transactionsDTOS=transactionsService.findByUser_UserId(id);
        return ResponseEntity.ok(transactionsDTOS);
    }
}
