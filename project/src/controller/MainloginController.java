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
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class MainloginController implements Initializable {

    @FXML
    private Button btnpatientlogin;
    @FXML
    private Button btnadminlogin;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void btnpatientlogin(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/PatientLogin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnpatientlogin.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Patient Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
//ViewManager.openLoginPage();
    }

    @FXML
    private void btnadminlogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/adminlogin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnadminlogin.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Patient Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
