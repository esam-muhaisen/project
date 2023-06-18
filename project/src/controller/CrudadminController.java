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
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
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
public class CrudadminController implements Initializable {

    @FXML
    private Button crudregisterid;
    @FXML
    private Button crudcreateid;
    @FXML
    private Button crudBookedid;
    @FXML
    private Button logoutcruded;
    @FXML
    private TableView<UserData> crudview;
    @FXML
    private TableColumn<UserData, Integer> idupdate;
    @FXML
    private TableColumn<UserData, String> usernameupdate;
    @FXML
    private TableColumn<UserData, String> passwordupdate;
    @FXML
    private TableColumn<UserData, String> firstnameupdate;
    @FXML
    private TableColumn<UserData, String> lastnameupdate;
    @FXML
    private TableColumn<UserData, Integer> ageupdate;
    @FXML
    private TableColumn<UserData, String> emailupdate;
    @FXML
    private TableColumn<UserData, String> phoneupdate;
    @FXML
    private TableColumn<UserData, String> genderupdate;
    @FXML
    private TableColumn<UserData, String> roleupdate;
    @FXML
    private TextField usernamefiled;
    @FXML
    private PasswordField passwordfiled;
    @FXML
    private TextField firstnamefiled;
    @FXML
    private TextField lastnamefiled;
    @FXML
    private TextField agefiled;
    @FXML
    private TextField emailfiled;
    @FXML
    private TextField phonefiled;
    @FXML
    private ComboBox<String> rolefiled;
    @FXML
    private ComboBox<String> genderfiled;
    @FXML
    private Button updateid;
    @FXML
    private Button deletedid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> items = Arrays.asList("male","female");

        genderfiled.getItems().addAll(items);
               List<String> items2 = Arrays.asList("admin", "patient");
        rolefiled.getItems().addAll(items2);
        
        
        idupdate.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameupdate.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordupdate.setCellValueFactory(new PropertyValueFactory<>("password"));
        firstnameupdate.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastnameupdate.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        ageupdate.setCellValueFactory(new PropertyValueFactory<>("age"));
        emailupdate.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneupdate.setCellValueFactory(new PropertyValueFactory<>("phone"));
        genderupdate.setCellValueFactory(new PropertyValueFactory<>("gender"));
        roleupdate.setCellValueFactory(new PropertyValueFactory<>("role"));
        
        loadPatients();
        
        
    }   
    
    private void loadPatients() {
        // JDBC connection and SQL statement
        String url = "jdbc:mysql://localhost:3306/clinic_appointments";
        String usernamee = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url, usernamee, password);
            String sql = "SELECT * FROM users ";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            // Clear existing items in the TableView
            crudview.getItems().clear();

            // Populate the TableView with the patient users
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String Password = resultSet.getString("password");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String gender = resultSet.getString("gender");
                String role = resultSet.getString("role");

                UserData userData = new UserData(id, username, firstName, lastName, age, email, phone, gender, role,Password);
                crudview.getItems().add(userData);
            }

            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
//    private void updateUser() {
//        UserData selectedUser = crudview.getSelectionModel().getSelectedItem();
//        String newFirstName = firstnamefiled.getText().trim();
//        String usernew = usernamefiled.getText().trim();
//        String passnew = passwordfiled.getText().trim();
//        String newLastName = lastnamefiled.getText().trim();
//        int newAge = Integer.parseInt(agefiled.getText().trim());
//        String newEmail = emailfiled.getText().trim();
//        String newPhone = phonefiled.getText().trim();
//        String newGender = genderfiled.getSelectionModel().getSelectedItem();
//        String newRole = rolefiled.getSelectionModel().getSelectedItem();
//
//        // Update the user in the database
//        String url = "jdbc:mysql://localhost:3306/clinic_appointments";
//        String username = "root";
//        String password = "";
//
//        try {
//            Connection connection = DriverManager.getConnection(url, username, password);
//            String sql = "UPDATE users SET first_name = ?, last_name = ?, age = ?, email = ?, phone = ?, gender = ?, role = ?,password=? WHERE id = ?";
//
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1, newFirstName);
//            statement.setString(2, newLastName);
//            statement.setInt(3, newAge);
//            statement.setString(4, newEmail);
//            statement.setString(5, newPhone);
//            statement.setString(6, newGender);
//            statement.setString(7, newRole);
//            statement.setString(8, passnew);
//            statement.setInt(9, selectedUser.getId());
//
//            int rowsUpdated = statement.executeUpdate();
//
//            if (rowsUpdated > 0) {
//                System.out.println("User updated successfully!");
//            } else {
//                System.out.println("Failed to update user.");
//            }
//
//            // Close the statement and connection
//            statement.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    
    
    
    
    
    

    @FXML
    private void oncrudregisterid(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/adminDashboard.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) crudregisterid.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Doctor Appointment Management System");
                    stage.show();
    }

    @FXML
    private void oncrudcreateid(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/CreateAppointmentadmin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) crudcreateid.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Doctor Appointment Management System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void oncrudBookedid(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/BookedAppointmentadmin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) crudBookedid.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Doctor Appointment Management System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setonlogoutcruded(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/mainlogin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) logoutcruded.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Doctor Appointment Management Sy/stem");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onupdateid(ActionEvent event) {
        UserData selectedUser = crudview.getSelectionModel().getSelectedItem();
        String newFirstName = firstnamefiled.getText().trim();
        String usernew = usernamefiled.getText().trim();
        String passnew = passwordfiled.getText().trim();
        String newLastName = lastnamefiled.getText().trim();
        int newAge = Integer.parseInt(agefiled.getText().trim());
        String newEmail = emailfiled.getText().trim();
        String newPhone = phonefiled.getText().trim();
        String newGender = genderfiled.getSelectionModel().getSelectedItem();
        String newRole = rolefiled.getSelectionModel().getSelectedItem();

        // Update the user in the database
        String url = "jdbc:mysql://localhost:3306/clinic_appointments";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE users SET first_name = ?, last_name = ?, age = ?, email = ?, phone = ?, gender = ?, role = ?,password=?,username=? WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newFirstName);
            statement.setString(2, newLastName);
            statement.setInt(3, newAge);
            statement.setString(4, newEmail);
            statement.setString(5, newPhone);
            statement.setString(6, newGender);
            statement.setString(7, newRole);
            statement.setString(8, passnew);
            statement.setString(9, usernew);
            statement.setInt(10, selectedUser.getId());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("User updated successfully!");
                selectedUser.setFirstName(newFirstName);
                selectedUser.setUsername(usernew);
            selectedUser.setLastName(newLastName);
            selectedUser.setAge(newAge);
            selectedUser.setEmail(newEmail);
            selectedUser.setPhone(newPhone);
            selectedUser.setGender(newGender);
            selectedUser.setRole(newRole);
            crudview.refresh();
            } else {
                System.out.println("Failed to update user.");
            }

            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
    }

    @FXML
    private void ondeleteid(ActionEvent event) {
         UserData selectedUser = crudview.getSelectionModel().getSelectedItem();

    // Delete the user from the database
        String url = "jdbc:mysql://localhost:3306/clinic_appointments";
        String username = "root";
        String password = "";

    try {
        Connection connection = DriverManager.getConnection(url, username, password);
        String sql = "DELETE FROM users WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, selectedUser.getId());

        int rowsDeleted = statement.executeUpdate();

        if (rowsDeleted > 0) {
            System.out.println("User deleted successfully!");
            // Remove the user from the TableView
            crudview.getItems().remove(selectedUser);
        } else {
            System.out.println("Failed to delete user.");
        }

        // Close the statement and connection
        statement.close();
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    
}
