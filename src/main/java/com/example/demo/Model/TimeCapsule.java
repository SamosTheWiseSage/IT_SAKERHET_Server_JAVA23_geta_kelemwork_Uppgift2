package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "time_capsule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeCapsule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String encryptedMessage;

   @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setEncryptedMessage(String encryptedMessage) {
        this.encryptedMessage = encryptedMessage;
    }

    public String getEncryptedMessage() {
        return encryptedMessage;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}