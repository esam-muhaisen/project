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
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField usernameid;
    @FXML
    private PasswordField passwordid;
    @FXML
    private TextField firstnameid;
    @FXML
    private TextField lastnameid;
    @FXML
    private TextField emailid;
    @FXML
    private TextField phoneid;
    @FXML
    private Button btnregisterid;
    @FXML
    private Button gobackregid;
    @FXML
    private TextField ageid;
    @FXML
    private ComboBox<String> coegnder;
    @FXML
    private ComboBox<String> roegnder;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               List<String> items = Arrays.asList("male","female");

        coegnder.getItems().addAll(items);
               List<String> items2 = Arrays.asList("admin", "patient");
        roegnder.getItems().addAll(items2);
    }    

    @FXML
    private void onusernameid(ActionEvent event) {
    }

    @FXML
    private void onpasswordid(ActionEvent event) {
    }

    @FXML
    private void onfirstnameid(ActionEvent event) {
    }

    @FXML
    private void onlastnameid(ActionEvent event) {
    }

    @FXML
    private void onemailid(ActionEvent event) {
    }

    @FXML
    private void onphoneid(ActionEvent event) {
    }

    @FXML
    private void onbtnregisterid(ActionEvent event) {
        
       String username = usernameid.getText();
        String password = passwordid.getText();
        String firstName = firstnameid.getText();
        String lastName = lastnameid.getText();
//        int age = Integer.parseInt(ageid.getText());
        
        String age = ageid.getText();
        String email = emailid.getText();
        String phone = phoneid.getText();
//        
//        RadioButton selectedGender = (RadioButton) gender.getSelectedToggle();
        String genderselect = coegnder.getSelectionModel().getSelectedItem();
        String roleselect = roegnder.getSelectionModel().getSelectedItem();
        
//        String genderselect = getSelectedToggleValue(gen);
//        String roleselect = getSelectedToggleValue(ro);

        // Validate the user input.
        if (!username.isEmpty() && !password.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !age.isEmpty()) {
//            errorLabel.setText("Please enter all required fields.");
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("correct");
                    alert.setHeaderText("correct added");
                    alert.setContentText("correct added. Thank You");
                    alert.showAndWait();
                try {
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_appointments", "root", "");

            // Create a prepared statement.
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password, first_name, last_name, age, email, phone, gender, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            // Set the parameters of the prepared statement.
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
        statement.setInt(5, Integer.parseInt(ageid.getText()));
            statement.setString(6, email);
            statement.setString(7, phone);
            statement.setString(8, genderselect);
            statement.setString(9, roleselect);

            // Execute the prepared statement.
            int rowsAffected = statement.executeUpdate();
            statement.executeUpdate();

            // Close the connection to the database.
            connection.close();
            usernameid.clear();

            // If rows were affected, then the user data was inserted successfully.
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
            return;
        }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid inputs");
                    alert.setContentText("Please enter a valid inputs.");
                    alert.showAndWait();
//            System.out.println("Please enter a valid inputs.");
        }
    } 

    @FXML
    private void ongobackregid(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/mainlogin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) gobackregid.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Doctor Appointment Management System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onageid(ActionEvent event) {
    }

private String getSelectedToggleValue(ToggleGroup gender) {
        Toggle selectedToggle = gender.getSelectedToggle();
        if (selectedToggle != null) {
            return selectedToggle.getUserData().toString();
        }
        return null;    
    }
    
}
