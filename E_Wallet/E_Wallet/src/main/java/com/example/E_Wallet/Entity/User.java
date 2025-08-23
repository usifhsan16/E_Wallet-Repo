package com.example.E_Wallet.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID UserId;
    @NotBlank
    private String UserName;
    @NotBlank
    @Column(unique = true)
    private String UserEmail;
    @NotBlank
    private String UserPassword;
    @OneToOne
    @JoinColumn(name = "WalletId")
    private Wallet UserWallet;
    @OneToMany(mappedBy = "user")
    List<Transactions> UserTransactions;

    @Override
    public String toString() {
        return "User{" +
                "UserPassword='" + UserPassword + '\'' +
                ", UserEmail='" + UserEmail + '\'' +
                ", UserName='" + UserName + '\'' +
                ", UserId=" + UserId +
                '}';
    }

    public Wallet getUserWallet() {
        return UserWallet;
    }

    public void setUserWallet(Wallet userWallet) {
        UserWallet = userWallet;
    }

    public List<Transactions> getUserTransactions() {
        return UserTransactions;
    }

    public void setUserTransactions(List<Transactions> userTransactions) {
        UserTransactions = userTransactions;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public UUID getUserId() {
        return UserId;
    }

    public void setUserId(UUID userId) {
        UserId = userId;
    }
}
