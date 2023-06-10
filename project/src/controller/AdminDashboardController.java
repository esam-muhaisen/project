/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AdminDashboardController implements Initializable {

    @FXML
    private Button logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void logout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/mainlogin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) logout.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Doctor Appointment Management System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
