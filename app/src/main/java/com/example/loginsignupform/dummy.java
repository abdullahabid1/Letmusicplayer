package com.example.loginsignupform;

public class dummy {

    String cnPassword,email,name,password,username;
    //null constructor
    public dummy() {
    }
   //constructor of class

    public dummy(String cnPassword, String email, String name, String password, String username) {
        this.cnPassword = cnPassword;
        this.email = email;
        this.name = name;
        this.password = password;
        this.username = username;
    }


    //getter and setter


    public String getCnPassword() {
        return cnPassword;
    }

    public void setCnPassword(String cnPassword) {
        this.cnPassword = cnPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
