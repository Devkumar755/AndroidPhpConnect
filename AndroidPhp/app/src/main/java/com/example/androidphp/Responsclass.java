package com.example.androidphp;

class Responsclass {
    private String name;
    private String password;
    private String email;
    private String number;
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }



    public Responsclass() {
    }

    public Responsclass(String name, String password, String email,String number) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.number = number;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}