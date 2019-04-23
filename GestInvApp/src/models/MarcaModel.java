/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author camilo
 */
public class MarcaModel {

    private int id;
    private String name;

    public MarcaModel(){

    }

    public MarcaModel(String name){
      Connection conexion = null;
      try{
        ConexionBD conexionPoll = new ConexionBD();
        conexion = conexionPoll.getBasicDataSource().getConnection();
        Statement query = conexion.createStatement();
        ResultSet result = query.executeQuery("SELECT * FROM marca WHERE nombre = '"+name+"'");
        if(result.next()){
          id = result.getInt(1);
          name = result.getString(2);
        }
      }
      catch(SQLException ex){

      }
      finally{
        try{ if(conexion != null) conexion.close(); }catch(Exception e){ System.out.println("[MarcaModel] Error: no se pudo liberar la conexión");}
      }
    }

    public ArrayList<ArrayList> getBrands(){
      ArrayList<ArrayList> marcas = new ArrayList<ArrayList>();
      Connection conexion = null;
      try{
        ConexionBD conexionPoll = new ConexionBD();
        conexion = conexionPoll.getBasicDataSource().getConnection();
        Statement query = conexion.createStatement();
        ResultSet result = query.executeQuery("SELECT * FROM marca");
        while(result.next()){
          ArrayList<String> marca = new ArrayList<String>();
          marca.add(result.getString(1));
          marca.add(result.getString(2));
          marcas.add(marca);
        }
        return marcas;
      }
      catch(SQLException ex){
        Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        return marcas;
      }
      finally{
        try{ if(conexion != null) conexion.close(); }catch(Exception e){ System.out.println("[MarcaModel] Erro: no se pudo liberar la conexión");}
      }
    }

    /*
    *
    **/
    public boolean save(String name){
      boolean saved = false;
      Connection conexion = null;
      try{
        ConexionBD conexionPoll = new ConexionBD();
        conexion = conexionPoll.getBasicDataSource().getConnection();
        PreparedStatement query = conexion.prepareStatement("INSERT INTO marca (nombre) VALUES (?)");
        query.setString(1,name);
        int result = query.executeUpdate();
        if(result > 0){
          saved = true;
        }
        return saved;
      }
      catch(SQLException ex){
        Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        return saved;
      }
      finally{
        try{ if(conexion != null) conexion.close(); }catch(Exception e){ System.out.println("[MarcaModel] Erro: no se pudo liberar la conexión");}
      }
    }



    //:::::::::::::::::::::GETTERS::::::::::::::::::

    public int getId(){
      return id;
    }

    public String gerName(){
      return name;
    }
}
