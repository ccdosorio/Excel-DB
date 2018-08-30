/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leerexcel;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christian
 */
public class ConnectionDB {

    private Connection connect = null;
    private PreparedStatement ps;
    private ResultSet rs;
    private ArrayList<Categoria> listaGenero = new ArrayList();
    
    public void connDataBase() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_movies","root","");
            System.out.println("!!!!Successfull!!!!");  
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void iniciarTrans(){
        try {
            connect.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void finalizarCommit(){
        try {
            connect.commit();
        } catch (Exception e) {
        }
    }
    
    public void enviarDatosPelicula(int id, String descripcion, int anio) throws SQLException{
          ps = connect.prepareStatement("INSERT INTO pelicula(codigo_pelicula, descripcion, anio) VALUES(?,?,?)");
          ps.setInt(1, id);
          ps.setString(2, descripcion);
          ps.setInt(3, anio);
          ps.executeUpdate();
    }
    
    public void enviarDatosCategoria(String descripcion) throws SQLException{
          ps = connect.prepareStatement("INSERT INTO genero(descripcion) VALUES(?)");
          ps.setString(1, descripcion);
          ps.executeUpdate();
    }   
    
    public void enviarDatosDualTable(int codigo_pelicula, int codigo_categoria){
        try {
            ps = connect.prepareStatement("INSERT INTO pelicula_genero(codigo_pelicula, codigo_categoria) VALUES(?,?)");
            ps.setInt(1, codigo_pelicula);
            ps.setInt(2, codigo_categoria);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public int traerCategoria(String categoria){
        int codigo_genero = 0; 
        try {
            if(listaGenero.isEmpty()){
                ps = connect.prepareStatement("SELECT * FROM genero");
                rs = ps.executeQuery();
                while(rs.next()){
                listaGenero.add( new Categoria(rs.getInt("codigo_categoria"), rs.getString("descripcion")));
                }
            }
            
            for (int i = 0; i < listaGenero.size(); i++) {
               if(listaGenero.get(i).getDescripcion().equals(categoria)){
                   codigo_genero = listaGenero.get(i).getCodigo_genero();
               }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codigo_genero;
    }
}
