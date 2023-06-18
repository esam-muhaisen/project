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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class FreeAppointmentadminController implements Initializable {

    @FXML
    private Button BookedAppointmentfreetab;
    @FXML
    private Button RegisteredPateintfreeApp;
    @FXML
    private Button logoutfree;
    @FXML
    private DatePicker createdate;
    @FXML
    private TextField createday;
    @FXML
    private TextField createtime;
    @FXML
    private Button createappointmentid;
    @FXML
    private Button crudidcreate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void setBookedAppointmentfreetab(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/BookedAppointmentadmin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) BookedAppointmentfreetab.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Doctor Appointment Management System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setRegisteredPateintfreeApp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/adminDashboard.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) RegisteredPateintfreeApp.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Doctor Appointment Management System");
                    stage.show();
    }

    @FXML
    private void logoutfree(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/mainlogin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) logoutfree.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Doctor Appointment Management System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void oncreateappointment(ActionEvent event) {
//         LocalDate date = LocalDate.parse(createdate.getText());
            LocalDate date = createdate.getValue();
//        String day = dayField.getText();
//        LocalTime time = LocalTime.parse(timeField.getText());
        String day = createday.getText();
        String time = createtime.getText();
        // Insert the appointment into the database
        if(date != null && !day.isEmpty() && !time.isEmpty()){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_appointments","root","")) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO appointments (appointment_date, appointment_day, appointment_time, status) VALUES (?, ?, ?, ?)");
            stmt.setDate(1,java.sql.Date.valueOf(date));
            stmt.setString(2, day);
            stmt.setString(3, time);
            stmt.setString(4, "free");
            stmt.executeUpdate();
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("correct");
                    alert.setHeaderText("correct added");
                    alert.setContentText("correct added. Thank You");
                    alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }else{
                     Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid inputs");
                    alert.setContentText("Please enter all inputs.");
                    alert.showAndWait();
        }
        
    }

    @FXML
    private void oncrudidcreate(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/crudadmin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) crudidcreate.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Doctor Appointment Management System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
