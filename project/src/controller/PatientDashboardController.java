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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class PatientDashboardController implements Initializable {

    @FXML
    private Button logoutid;
    @FXML
    private Button waitingApp;
    @FXML
    private Button freeApp;
    @FXML
    private Button finishedApp;
    @FXML
    private TableColumn<Appointment, String> AppDaycolumn;
    @FXML
    private TableColumn<Appointment, String> AppDatacolumn;
    @FXML
    private TableColumn<Appointment, String> AppTimecolumn;
    @FXML
    private TableView<Appointment> freeAppointments;
    @FXML
    private Button bookedAppid;
private ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        TableColumn<Appointment, String> dayColumn = new TableColumn<>("Day");
        
        AppDaycolumn.setCellValueFactory(cellData -> cellData.getValue().dayProperty());
        TableColumn<Appointment, String> dateColumn = new TableColumn<>("Date");
        AppDatacolumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        TableColumn<Appointment, String> timeColumn = new TableColumn<>("Time");
        AppTimecolumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
//        freeAppointments.getColumns().addAll(dayColumn, dateColumn, timeColumn);

        // populate the table with appointment data
        List<Appointment> appointments = getAppointmentsFromDatabase();
        freeAppointments.getItems().addAll(appointments);
        // TODO
    }
    
    private List<Appointment> getAppointmentsFromDatabase() {
        List<Appointment> appointments = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_appointments","root","");
             ResultSet rs = conn.createStatement().executeQuery("SELECT appointment_day, appointment_date, appointment_time, id FROM appointments where status ='free'")) {
            while (rs.next()) {
                String day = rs.getString("appointment_day");
                String date = rs.getString("appointment_date");
                String time = rs.getString("appointment_time");
                int i = rs.getInt("id");
                
                appointments.add(new Appointment(i,day, date, time));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }

//    private void logoutid(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/mainlogin.fxml"));
//            Parent root = loader.load();
//            Stage stage = (Stage) waitingApp.getScene().getWindow();
//            stage.setScene(new Scene(root));
//            stage.setTitle("Doctor Appointment Management System");
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    @FXML
    private void setonwaitingApp(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/WaitingAppointments.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) waitingApp.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Doctor Appointment Management System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void setonlogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/mainlogin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) waitingApp.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Doctor Appointment Management System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setonfreeApp(ActionEvent event) {
    }

    @FXML
    private void setonfinishedApp(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/FinishedAppointments.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) finishedApp.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Doctor Appointment Management System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onbookedAppid(ActionEvent event) {
         Appointment selectedAppointment = freeAppointments.getSelectionModel().getSelectedItem();

        if (selectedAppointment == null) {
            // display an error message if no appointment is selected
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No appointment selected");
            alert.setContentText("Please select an appointment to book.");
            alert.showAndWait();
            return;
        }

        // check if the selected appointment is already booked
//        if (selectedAppointment.equals("booked")) {
//            // display an error message if the appointment is already booked
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Appointment not available");
//            alert.setContentText("The selected appointment is not available. Please select a different appointment.");
//            alert.showAndWait();
//            return;
//        }

        // update the appointment status to booked
//        selectedAppointment.setStatus("booked");
        boolean success = updateAppointmentInDatabase(selectedAppointment);

        if (success) {
            // display a success message if the appointment was updated in the database
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Appointment booked");
            alert.setContentText("The appointment has been booked successfully.");
            alert.showAndWait();

            // refresh the table to reflect the updated data
            appointments.clear();
            appointments.addAll(getAppointmentsFromDatabase());
            freeAppointments.refresh();
        } else {
            // display an error message if the appointment could not be updated in the database
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Appointment not booked");
            alert.setContentText("An error occurred while booking the appointment. Please try again later.");
            alert.showAndWait();
        }
    }

     private boolean updateAppointmentInDatabase(Appointment appointment) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_appointments","root","")) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE appointments SET status = 'booked' WHERE id = ?");
//            stmt.setString(1, "booked");
            stmt.setInt(1, appointment.getId());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}


