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
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class BookedAppointmentadminController implements Initializable {

    @FXML
    private Button FreeAppointmenttapbooked;
    @FXML
    private Button RegisteredPateinttapbooked;
    @FXML
    private Button logoutbooked;
    @FXML
    private TextField filedsearchid;
    @FXML
    private Button searchid;
    @FXML
    private TextField textfiledinput;
    @FXML
    private Button commentforinput;
    @FXML
    private TableColumn<User, String> username;
    @FXML
    private TableColumn<Appointment, String> AppDay;
    @FXML
    private TableColumn<Appointment, String> AppDate;
    @FXML
    private TableColumn<Appointment, String> AppTime;
    @FXML
    private TableColumn<BookedAppointment, String> statusid;
    @FXML
    private TableColumn<BookedAppointment, String> commentid;
    @FXML
    private TableView<Appointment> bookedview;
    @FXML
    private Button crudidbooked;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AppDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        AppDay.setCellValueFactory(new PropertyValueFactory<>("day"));
        AppTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        statusid.setCellValueFactory(new PropertyValueFactory<>("status"));
        commentid.setCellValueFactory(new PropertyValueFactory<>("comment"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));

        // Load the data from the database
        List<Appointment> appointments = loadAppointmentsFromDatabase();

        // Set the table items to the appointments list
        bookedview.setItems(FXCollections.observableArrayList(appointments));
        // TODO
    }    

    
    private List<Appointment> loadAppointmentsFromDatabase() {
        List<Appointment> appointments = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_appointments","root","")) {
            // Load the appointments
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM appointments");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String date = rs.getString("appointment_date");
                String day = rs.getString("appointment_day");
                String time = rs.getString("appointment_time");
                String status = rs.getString("status");
                appointments.add(new Appointment(id, date, day, time, status));
            }

            // Load the booked appointments
            stmt = conn.prepareStatement("SELECT * FROM booked_appointments");
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int appointmentId = rs.getInt("appointment_id");
                int userId = rs.getInt("user_id");
                String status = rs.getString("status");
                String comment = rs.getString("doctor_comment");
                for (Appointment appointment : appointments) {
                    if (appointment.getId() == appointmentId) {
                        appointment.setBookedAppointment(new BookedAppointment(id, appointment, getUserFromDatabase(userId), status, comment));
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }
    
    private User getUserFromDatabase(int userId) {
        User user = null;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_appointments","root","")) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String gender = rs.getString("gender");
                String role = rs.getString("role");
                user = new User(id, username, password, firstName, lastName, age, email, phone, gender, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    
    
    
    
    
    
    
    
    
    @FXML
    private void setFreeAppointmenttapbooked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/CreateAppointmentadmin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) FreeAppointmenttapbooked.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Doctor Appointment Management System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setonRegisteredPateinttapbooked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/adminDashboard.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) RegisteredPateinttapbooked.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Doctor Appointment Management System");
                    stage.show();
    }

    @FXML
    private void setonlogoutbooked(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/mainlogin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) logoutbooked.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Doctor Appointment Management System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onsearch(ActionEvent event) {
    }

    @FXML
    private void oncommentforinput(ActionEvent event) {
    }

    @FXML
    private void oncrudidbooked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/crudadmin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) crudidbooked.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Doctor Appointment Management System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
