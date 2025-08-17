package com.example.E_Wallet.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID TransactionId;
    private double TransactionAmount;
    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;
}
