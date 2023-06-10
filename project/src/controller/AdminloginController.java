/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AdminloginController implements Initializable {

    @FXML
    private Button switchadmin;
    @FXML
    private TextField usernameadmin;
    @FXML
    private PasswordField passwordadminid;
    @FXML
    private Button btnloginadmin;
    @FXML
    private Button registeradminid;

        Connection c;
        PreparedStatement ps = null;
        ResultSet r;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onswitchadmin(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/mainlogin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) switchadmin.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Doctor Appointment Management System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onusernameadmin(ActionEvent event) {
    }

    @FXML
    private void onpasswordadminid(ActionEvent event) {
    }

    @FXML
    private void onbtnloginadmin(ActionEvent event) {
        String username = usernameadmin.getText();
        String password = passwordadminid.getText();
        if (username.trim().isEmpty() || password.trim().isEmpty()) {
//            labelerrorid.setText("Invalid username or password.");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid Username or Password");
                    alert.setContentText("Please enter a valid username and password.");
                    alert.showAndWait();

        }else {
            try {
//                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_appointments", "root", "");
                Connection c =Database.getInstance().getConnection();
                ps = c.prepareStatement("select * from users where username=? and password=? and role= 'admin'");
                ps.setString(1, username);
                ps.setString(2, password);
                r=ps.executeQuery();
                
//                Statement statement = c.createStatement();
                   
//                String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
//                String query = "SELECT * FROM users WHERE username = '" + username;

//                ResultSet resultSet = statement.executeQuery(query);

                // Check if the user exists.
                if (r.next()) {
                    // The user exists, so login the user and show the main dashboard.
                    Stage mainStage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/adminDashboard.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) btnloginadmin.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Doctor Appointment Management System");
                    stage.show();

//                    Scene scene = new Scene(loader.load());
//                    mainStage.setScene(scene);
//                    mainStage.show();
                } else {
                    // The user does not exist, so show an error message.
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid Username or Password");
                    alert.setContentText("Please enter a valid username and password.");
                    alert.showAndWait();
                }

                // Close the connection to the database.
                c.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    private void onregisteradminid(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/Register.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) registeradminid.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Register Page");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
