package com.example.E_Wallet.DTO;

import com.example.E_Wallet.Entity.User;
import com.example.E_Wallet.Entity.Wallet;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class UserDTO {
    private UUID UserId;

    public UUID getUserId() {
        return UserId;
    }

    public void setUserId(UUID userId) {
        UserId = userId;
    }

    @NotBlank
    private String UserName;
    @NotBlank
    @Email
    private String UserEmail;
    @NotBlank
    private WalletDTO UserWallet;

    public WalletDTO getUserWallet() {
        return UserWallet;
    }

    public void setUserWallet(WalletDTO userWallet) {
        UserWallet = userWallet;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserName() {
        return UserName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public UserDTO toDTO(User user){
        UserDTO dto=new UserDTO();
        dto.UserId=user.getUserId();
        dto.UserName=user.getUserName();
        dto.UserEmail=user.getUserEmail();
        WalletDTO walletDTO=new WalletDTO();
        walletDTO=walletDTO.toDto(user.getUserWallet());
        dto.setUserWallet(walletDTO);
        return dto;
    }

    public User toEntity(UserDTO dto){
        User user= new User();
        user.setUserId(dto.getUserId());
        user.setUserName(dto.getUserName());
        user.setUserEmail(dto.getUserEmail());
        Wallet wallet= new Wallet();
        wallet=dto.getUserWallet().toEntity(dto.UserWallet);
        user.setUserWallet(wallet);
        return user;
    }

}
