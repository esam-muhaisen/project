/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author hp
 */
public class Admindash extends Stage{
    private Scene registeredPatiantScene;
    private Scene FreeAppointmentsScene;
    private Scene BookedAppointmentsScene;
    private Scene CreatAppointmentsScene;
    private Scene UpdateUserScene;




    public Admindash() throws IOException {
        
        FXMLLoader registeredPatiantLoader = new FXMLLoader(getClass().getResource("../viewfxml/adminDashboard.fxml"));
        Parent registeredPatiantRoot = registeredPatiantLoader.load();     
        registeredPatiantScene = new Scene(registeredPatiantRoot);
        
        
        FXMLLoader FreeAppointmentsLoader = new FXMLLoader(getClass().getResource("../viewfxml/CreateAppointmentadmin.fxml"));
        Parent FreeAppointmentsRoot = FreeAppointmentsLoader.load();     
        FreeAppointmentsScene = new Scene(FreeAppointmentsRoot);
        
        FXMLLoader BookedAppointmentsLoader = new FXMLLoader(getClass().getResource("../viewfxml/BookedAppointmentadmin.fxml"));
        Parent BookedAppointmentsRoot = BookedAppointmentsLoader.load();     
        BookedAppointmentsScene = new Scene(BookedAppointmentsRoot);
        

        
        this.setScene(registeredPatiantScene);
        this.setTitle("Admin Dashboard");
        
        
    }
    public void changeSceneToregisteredPatiant(){
        this.setScene(registeredPatiantScene);
    }
    public void changeSceneToFreeAppointments(){
        this.setScene(FreeAppointmentsScene);
    }
    
    public void changeSceneToBookedAppointments(){
        this.setScene(BookedAppointmentsScene);
    }
    
    public void changeSceneToCreatAppointments(){
        this.setScene(CreatAppointmentsScene);
    }
    public void changeSceneToUpdateUser(){
        this.setScene(UpdateUserScene);
    }
}
