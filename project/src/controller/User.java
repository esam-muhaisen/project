/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author hp
 */
class User {
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String phone;
    private String gender;
    private String role;

    public User(int id, String username, String password, String firstName, String lastName, int age, String email, String phone, String gender, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.role = role;
    }
}
