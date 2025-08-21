package com.example.E_Wallet.DTO;

import com.example.E_Wallet.Entity.User;
import com.example.E_Wallet.Entity.Wallet;

public class UserDTO {
    private String UserName;
    private String UserEmail;
    private WalletDTO UserWallet;
    private String UserPassword;

    public WalletDTO getUserWallet() {
        return UserWallet;
    }

    public void setUserWallet(WalletDTO userWallet) {
        UserWallet = userWallet;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
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
        dto.UserName=user.getUserName();
        dto.UserEmail=user.getUserEmail();
        dto.UserPassword=user.getUserPassword();
        return dto;
    }

    public User toEntity(UserDTO dto){
        User user= new User();
        user.setUserName(dto.getUserName());
        user.setUserEmail(dto.getUserEmail());
        user.setUserPassword(dto.getUserPassword());
        return user;
    }

}
