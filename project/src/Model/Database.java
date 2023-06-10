/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class Database {
    private static Database db;
    private Database(){
        
    }
    public static Database getInstance(){
        if(db == null){
              db= new Database();
        }else{
            return db;
        }
        return db;
    }
    
    public Connection getConnection()throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_appointments","root","");
        return conn;
    }
}
