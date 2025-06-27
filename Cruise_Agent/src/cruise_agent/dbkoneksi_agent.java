/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cruise_agent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ni Kadek Candra Dewi 
 * 2301010091 
 * 27 June 2025
 */
public class dbkoneksi_agent {
    
    static String JDBC_DRIVER ="com.mysql.cj.jdbc.Driver";
    static String DB_USER="root";
    static String DB_PASSWORD="";
    static String DB_URL="jdbc:mysql://localhost:3306/cruise_agent";
    
    public static Connection koneksi(){
        try{
            Class.forName(JDBC_DRIVER);
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        }catch(ClassNotFoundException | SQLException err){
            System.out.println("Gagal Koneksi ke Database");
        }
        return null;
    }
}
