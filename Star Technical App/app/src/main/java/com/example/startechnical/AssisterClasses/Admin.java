package com.example.startechnical.AssisterClasses;

public class Admin {
    String       Name;
    String       PhonNo;
    String      Password;

    public Admin() {
    }

    public Admin(String name, String phonNo, String password) {
        Name = name;
        PhonNo = phonNo;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhonNo() {
        return PhonNo;
    }

    public void setPhonNo(String phonNo) {
        PhonNo = phonNo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
