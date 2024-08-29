package com.codegym;

public class User {
    private String name;
    private int age;
    private String gender;
    private String address;
    private String userName;
    private String password;

    public User(String name, int age, String gender, String address, String userName, String password) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.userName = userName;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
