package com.example.E_Wallet.Controller;

import com.example.E_Wallet.DTO.UserDTO;
import com.example.E_Wallet.Entity.User;
import com.example.E_Wallet.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/GetUser")
    public UserDTO GetUser(@RequestParam UUID id){
        return userService.FindById(id);
    }

    @PostMapping("/SaveUser")
    public UserDTO SaveUser(@RequestBody User entity){
        userService.Save(entity);
        entity.getUserWallet().setWalletBalance(0);
        UserDTO userDTO= new UserDTO();
        userDTO=userDTO.toDTO(entity);
        return userDTO;
    }

}
