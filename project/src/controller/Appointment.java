/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.BookedAppointment;
import controller.BookedAppointment;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author hp
 */
public class Appointment {

    public int id;
     public final SimpleStringProperty day;
    public final SimpleStringProperty date;
    public final SimpleStringProperty time;
    public String status;
     public BookedAppointment bookedAppointment;

    public Appointment(int id,String day, String date, String time) {
        this.id = id;
        this.day = new SimpleStringProperty(day);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
    }

    public Appointment(String day, String date, String time) {
                this.day = new SimpleStringProperty(day);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
    }
     public Appointment(int id, String date, String day, String time, String status) {
        this.id = id;
        this.date = new SimpleStringProperty(date);
        this.day = new SimpleStringProperty(day);
        this.time = new SimpleStringProperty(time);
        this.status = status;
    }



//    Appointment(int id, LocalDate date, String day, LocalTime time, String booked) {
//        
//    }

    public String getDay() {
        return day.get();
    }

    public SimpleStringProperty dayProperty() {
        return day;
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the bookedAppointment
     */
    public BookedAppointment getBookedAppointment() {
        return bookedAppointment;
    }

    /**
     * @param bookedAppointment the bookedAppointment to set
     */
    public void setBookedAppointment(BookedAppointment bookedAppointment) {
        this.bookedAppointment = bookedAppointment;
    }
}
