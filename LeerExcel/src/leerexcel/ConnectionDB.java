/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leerexcel;

import java.sql.*;

/**
 *
 * @author programacion
 */
public class ConnectionDB {

    private Connection connect = null;
    
    public void connDataBase() throws Exception {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/movies?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&user=osorio&password=osorio");
            System.out.println("!!!!Successfull!!!!");  
            
        } catch (Exception e) {
            throw e;
        }
        
    }
    
    public boolean enviarDatos(String sql) throws SQLException{
        
        return connect.createStatement().execute(sql);
    }
    
}
