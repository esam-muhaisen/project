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
class AppointmentData {
    private int id;
    private String day;
    private String date;
    private String time;
    private String status;
    private String comment;
    private String username;

    public AppointmentData(int id, String day, String date, String time, String status, String comment, String username) {
        this.id = id;
        this.day = day;
        this.date = date;
        this.time = time;
        this.status = status;
        this.comment = comment;
        this.username = username;
    }

    AppointmentData(String day, String date, String time, String status, String comment, String usernamee) {
            this.day = day;
        this.date = date;
        this.time = time;
        this.status = status;
        this.comment = comment;
        this.username = username;
    }



    public int getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public String getComment() {
        return comment;
    }

    public String getUsername() {
        return username;
    }
}
