package com.example.E_Wallet.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String TransactionType;
    private double TransactionAmount;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "UserID")
    private User user;
}
