/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class WaitingAppointmentsController implements Initializable {

    @FXML
    private Button freetapwaiting;
    @FXML
    private Button waitingApptapwaiting;
    @FXML
    private Button finishApptapwaiting;
    @FXML
    private Button logoutwaiting;
    @FXML
    private TableView<Appointment> waitingviewid;
    @FXML
    private TableColumn<Appointment, String> AppointmentsDayidwaiting;
    @FXML
    private TableColumn<Appointment, String> AppointmentsDateidwaiting;
    @FXML
    private TableColumn<Appointment, String> AppointmentsTimeidwaiting;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        AppointmentsDateidwaiting.setCellValueFactory(new PropertyValueFactory<>("date"));
//        AppointmentsTimeidwaiting.setCellValueFactory(new PropertyValueFactory<>("time"));
//        AppointmentsDayidwaiting.setCellValueFactory(new PropertyValueFactory<>("day"));
//
//        // Retrieve the waiting appointments from the database
//        List<Appointment> waitingAppointments = getWaitingAppointmentsFromDatabase();
//
//        // Populate the table with the waiting appointments
//        waitingviewid.getItems().addAll(waitingAppointments);
        // TODO
        
        AppointmentsDayidwaiting.setCellValueFactory(cellData -> cellData.getValue().dayProperty());
        TableColumn<Appointment, String> dateColumn = new TableColumn<>("Date");
        AppointmentsDateidwaiting.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        TableColumn<Appointment, String> timeColumn = new TableColumn<>("Time");
        AppointmentsTimeidwaiting.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
//        freeAppointments.getColumns().addAll(dayColumn, dateColumn, timeColumn);

        // populate the table with appointment data
        List<Appointment> appointments = getWaitingAppointmentsFromDatabase();
        waitingviewid.getItems().addAll(appointments);
    }
        private List<Appointment> getWaitingAppointmentsFromDatabase() {
        List<Appointment> waitingAppointments = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_appointments","root","")) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM appointments INNER JOIN booked_appointments ON appointments.id = booked_appointments.appointment_id WHERE booked_appointments.status = 'waiting'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("appointments.id");
                String date = rs.getString("appointment_date");
                String time = rs.getString("appointment_time");
                String day = rs.getString("appointment_day");
                Appointment appointment = new Appointment(id,day,date,time);
                waitingAppointments.add(new Appointment(id,day, date, time));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return waitingAppointments;
    }    

    @FXML
    private void setonfreetapwaiting(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/PatientDashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) freetapwaiting.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Doctor Appointment Management System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setonwaitingApptapwaiting(ActionEvent event) {
    }

    @FXML
    private void setonfinishApptapwaiting(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/FinishedAppointments.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) finishApptapwaiting.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Doctor Appointment Management System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setonlogoutwaiting(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/mainlogin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) logoutwaiting.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Doctor Appointment Management System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
