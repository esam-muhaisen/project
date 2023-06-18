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
class BookedAppointment {
    private int id;
    private Appointment appointment;
    private User user;
    private String status;
    private String comment;

    public BookedAppointment(int id, Appointment appointment, User user, String status, String comment) {
        this.id = id;
        this.appointment = appointment;
        this.user = user;
        this.status = status;
        this.comment = comment;
    }
}
