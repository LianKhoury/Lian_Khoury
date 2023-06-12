package com.example.liankhoury;

public class ReadWriteUserDetails {
    private String textPhoneNum;

    public ReadWriteUserDetails() {
        // Default constructor required for Firebase Realtime Database
    }

    public ReadWriteUserDetails(String textPhoneNum) {
        this.textPhoneNum = textPhoneNum;
    }

    public String getTextPhoneNum() {
        return textPhoneNum;
    }

    public void setTextPhoneNum(String textPhoneNum) {
        this.textPhoneNum = textPhoneNum;
    }
}