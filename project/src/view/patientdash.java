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
public class patientdash extends Stage{
     private Scene FreeAppointmentsScene;
    private Scene WaitingAppointmentsScene;
    private Scene FinishedAppointmentsScene;

    //private Scene operationsScene;

    public patientdash() throws IOException {
        
       
        
        
        //load CreateUser FXML File in CreateUser Scene
        FXMLLoader FreeAppointmentsLoader = new FXMLLoader(getClass().getResource("../viewfxml/PatientDashboard.fxml"));
        Parent FreeAppointmentsRoot = FreeAppointmentsLoader.load();     
        FreeAppointmentsScene = new Scene(FreeAppointmentsRoot);
        
        //load AccountsManagment FXML File in AccountsManagment Scene
        FXMLLoader WaitingAppointmentsLoader = new FXMLLoader(getClass().getResource("../viewfxml/WaitingAppointments.fxml"));
        Parent WaitingAppointmentsRoot = WaitingAppointmentsLoader.load();     
        WaitingAppointmentsScene = new Scene(WaitingAppointmentsRoot);
        
        FXMLLoader finishedAppointmentsLoader = new FXMLLoader(getClass().getResource("../viewfxml/FinishedAppointments.fxml"));
        Parent finishedAppointmentsRoot = finishedAppointmentsLoader.load();     
        FinishedAppointmentsScene = new Scene(finishedAppointmentsRoot);
        
        // Set Main Scene in Admin Dasboard ( UsersManagment Scene )
        this.setScene(FreeAppointmentsScene);
        this.setTitle("Admin Dashboard");
        
        
    }
    
    public void changeSceneToFreeAppointments(){
        this.setScene(FreeAppointmentsScene);
    }
    
    public void changeSceneToBookedAppointments(){
        this.setScene(WaitingAppointmentsScene);
    }
    
    public void changeSceneToFinishedAppointments(){
        this.setScene(FinishedAppointmentsScene);
    }
}
