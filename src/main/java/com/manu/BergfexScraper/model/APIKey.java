package com.manu.BergfexScraper.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class APIKey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String username;
    private String keyValue = UUID.randomUUID().toString();

    public APIKey(){}

    public APIKey(Long id, String username, String key) {
        Id = id;
        this.username = username;
        this.keyValue = key;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    @Override
    public String toString() {
        return "APIKey{" +
                "Id=" + Id +
                ", username='" + username + '\'' +
                ", key='" + keyValue + '\'' +
                '}';
    }
}
