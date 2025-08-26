package com.example.E_Wallet.Controller;

import com.example.E_Wallet.DTO.UserDTO;
import com.example.E_Wallet.Entity.User;
import com.example.E_Wallet.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/GetUser")
    public ResponseEntity<UserDTO> GetUser(@RequestParam UUID id){
        UserDTO userDTO=userService.FindById(id);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/SaveUser")
    public ResponseEntity<UserDTO> SaveUser(@RequestBody User entity){
        userService.Save(entity);
        entity.getUserWallet().setWalletBalance(0);
        UserDTO userDTO= new UserDTO();
        userDTO=userDTO.toDTO(entity);
        return ResponseEntity.ok(userDTO);
    }
    @PostMapping("/DeleteUser")
    public ResponseEntity<Void> DeleteUser(@RequestParam UUID id){
        userService.Delete(id);
        return ResponseEntity.noContent().build();
    }
}
