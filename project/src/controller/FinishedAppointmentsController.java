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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class FinishedAppointmentsController implements Initializable {

    @FXML
    private Button waitingAppfinfshed;
    @FXML
    private Button freeAppfinfshed;
    @FXML
    private Button finishtap;
    @FXML
    private Button logoutfinished;
    @FXML
    private TableView<Appointment> finishview;
    @FXML
    private TableColumn<Appointment, String> AppDaycolumnfinish;
    @FXML
    private TableColumn<Appointment, String> AppDatecolumnfinish;
    @FXML
    private TableColumn<Appointment, String> AppTimecolumnfinish;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AppDaycolumnfinish.setCellValueFactory(cellData -> cellData.getValue().dayProperty());
        TableColumn<Appointment, String> dateColumn = new TableColumn<>("Date");
        AppDatecolumnfinish.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        TableColumn<Appointment, String> timeColumn = new TableColumn<>("Time");
        AppTimecolumnfinish.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
//        freeAppointments.getColumns().addAll(dayColumn, dateColumn, timeColumn);

        // populate the table with appointment data
        List<Appointment> appointments = getFinishAppointmentsFromDatabase();
        finishview.getItems().addAll(appointments);
    }    
    
    
    
    
    
    
    private List<Appointment> getFinishAppointmentsFromDatabase() {
        List<Appointment> waitingAppointments = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_appointments","root","")) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM appointments INNER JOIN booked_appointments ON appointments.id = booked_appointments.appointment_id WHERE booked_appointments.status = 'finished'");
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
    private void setonwaitingAppfinfshed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/WaitingAppointments.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) waitingAppfinfshed.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Doctor Appointment Management System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setonfreeAppfinfshed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/PatientDashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) freeAppfinfshed.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Doctor Appointment Management System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setonfinishtap(ActionEvent event) {
    }

    @FXML
    private void setonlogoutfinished(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/mainlogin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) logoutfinished.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Doctor Appointment Management System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
