package com.example.liankhoury;

public class ReadWriteUserDetails {
    private String phone, name;

    public ReadWriteUserDetails() {
        // Default constructor required for Firebase Realtime Database
    }

    public ReadWriteUserDetails(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }
}