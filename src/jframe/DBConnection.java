/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jframe;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author yared
 */
public class DBConnection {
   
      public static Connection getConnection(){
    try{
            String driver="com.mysql.cj.jdbc.Driver";
            String datbaseurl="jdbc:mysql://localhost:3306/universitylibrary";
            String username="root";
            String password="";
            Class.forName(driver);
           Connection conn=DriverManager.getConnection(datbaseurl,username,password);
          //  System.out.println("Database Connected");
            return conn;
        }catch (ClassNotFoundException | SQLException e){
          System.out.println(e);
        //    JOptionPane.showConfirmDialog(this, "Database  Connection feild !!");
           
        }
        return null;
    }
      
}

