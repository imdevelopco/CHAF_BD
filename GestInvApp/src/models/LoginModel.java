/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Camilo Arias
 */
public class LoginModel extends ConexionBD{

    private String user;
    private String pass;
    private int terceroId;

    public boolean validate(){
        
            ConexionBD conexionPoll = new ConexionBD();
            Connection conexion = null;
        try {    
            // BasicDataSource nos reserva una conexion y nos la devuelve.
            conexion = conexionPoll.getBasicDataSource().getConnection();
            PreparedStatement query1 = conexion.prepareStatement("SELECT encriptar_dato(?)");
            query1.setString(1,pass);
            ResultSet res = query1.executeQuery();
            while(res.next()){
                pass = res.getString(1);
            }
            PreparedStatement query2 = conexion.prepareStatement("SELECT * FROM usuario WHERE lower(login_usuario) = lower(?) and contraseña = ?");  
            query2.setString(1,user);    
            query2.setString(2,pass);
            ResultSet response = query2.executeQuery();
            if(response.next()){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        
        } finally {
           // En realidad no cierra la conexion, sino que avisa al pool de que
           // esta conexión queda libre.
           try {
             if (conexion != null){
               conexion.close();
               System.out.println("[loginModel2] conexion liberada");
             }
           } catch(Exception e) { }
        }
        
    }

    public void setUser(String user){
      this.user = user;
    }

    public void setPass(String pass){
      this.pass = pass;
    }

    public String getUser(){
      return this.user;
    }

    public String getPass(){
      return this.pass;
    }
    
    public void serTerceroId(int terId){
      this.terceroId = terId;
    }

    public int getTerceroId(){
      return this.terceroId;
    }
}
