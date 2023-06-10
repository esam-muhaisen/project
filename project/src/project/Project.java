/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import Model.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.MainloginController;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class Project extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewfxml/mainlogin.fxml"));
                Parent root = loader.load(); 
                        stage.setTitle("Doctor Appointment Management System");
                        stage.setScene(new Scene(root));
                        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        launch(args);

    }
    
}
