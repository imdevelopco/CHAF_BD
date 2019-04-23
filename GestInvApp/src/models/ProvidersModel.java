/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import models.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 *
 * @author camilo
 */
public class ProvidersModel {

    private int cantidadProveedores;
    private ArrayList<ArrayList> proveedores = new ArrayList<ArrayList>();
    private int tercero_id;
    private String tipoId;
    private int numeroDoc;
    private String direccion;
    private String nameProvider;
    private String telefono;        
    private boolean estado;

    public ProvidersModel(){
        this.setCantidadProveedores();
    }
    
    public ProvidersModel(String name){
      Connection conexion = null;
      try{
        ConexionBD conexionPoll = new ConexionBD();
        conexion = conexionPoll.getBasicDataSource().getConnection();
        Statement query = conexion.createStatement();
        ResultSet response = query.executeQuery("SELECT t.tercero_id, p.estado FROM tercero as t INNER JOIN proveedor as p ON p.tercero_id = t.tercero_id WHERE t.nombre_tercero = '"+name+"'");
        while(response.next()){
          tercero_id = response.getInt(1);
          estado = response.getBoolean(2);
        }
      }
      catch(SQLException ex){
          Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
      }
      finally{
        try{ if(conexion != null) conexion.close();}catch(Exception e){ System.out.println("[ProveedorModel] Error: no fue posible liberar la conexión");}
      }
    }
    
    private void setCantidadProveedores() {
        ConexionBD con = new ConexionBD();
        Connection conex = null;
        try {
            conex = con.getBasicDataSource().getConnection();
            Statement query = conex.createStatement();
            ResultSet response = query.executeQuery("SELECT t.tipo_id,t.numero_id, t.direccion,t.nombre_tercero,t.telefono AS nombre FROM tercero AS t \n" +
                    "NATURAL JOIN proveedor AS u");
            while(response.next()){
                this.cantidadProveedores++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
       }
       finally{
         try{ if(conex != null) conex.close();}catch(Exception e){ System.out.println("[ProvidersModel] Error: no fue posible liberar la conexión");}
       }
    }
    
    public int getTercero_id(){
      return tercero_id;
    }

    public boolean getEstado(){
      return estado;
    }

    public String getTipoId() {
        return tipoId;
    }

    public int getNumeroDoc() {
        return numeroDoc;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNameProvider() {
        return nameProvider;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getCantProveedores(){
        return this.cantidadProveedores;
    }

    public ArrayList getProveedores(){
      ArrayList<ArrayList> proveedores = new ArrayList<ArrayList>();
      Connection conexion = null;
      try{
        ConexionBD conexionPoll = new ConexionBD();
        conexion = conexionPoll.getBasicDataSource().getConnection();
        Statement query = conexion.createStatement();
        ResultSet response = query.executeQuery("SELECT t.tercero_id, t.tipo_id, t.numero_id, t.direccion, t.nombre_tercero, t.telefono, p.estado FROM tercero as t INNER JOIN proveedor as p ON p.tercero_id = t.tercero_id");
        while(response.next()){
            int i= 1;
            ArrayList<String> proveedor = new ArrayList<String>();
            while(i<7){
                proveedor.add(response.getString(i));
                i++;
            }
            proveedores.add(proveedor);
        }

        conexion.close();
        return proveedores;
      }
      catch(SQLException ex){
          Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
          return proveedores;
      }
      finally{
        try{ if(conexion != null) conexion.close();}catch(Exception e){ System.out.println("[ProveedorModel] Error: no fue posible liberar la conexión");}
      }
    }

    public void insertarProveedor(String tipoDoc,int numDoc,String dir,String name,String tel){
        Connection conex = null;
        try {
            ConexionBD con = new ConexionBD();
            conex = con.getBasicDataSource().getConnection();
            PreparedStatement query = conex.prepareStatement(" SELECT insertar_proveedor(?,?,?,?,?)");
            query.setString(1, tipoDoc);
            query.setInt(2,(int) numDoc);
            query.setString(3, dir);
            query.setString(4,name);
            query.setString(5, tel);
            query.execute();
            System.out.println("[ProvidersModel]: se inserto el tercero: " + name);
        } catch (SQLException ex) {
            Logger.getLogger(CustomersModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
          try{ if(conex != null) conex.close();}catch(Exception e){ System.out.println("[ProvidersModel] Error: no fue posible liberar la conexión");}
        }
    }
       
    public void actualizarProveedor(int terId,String tipoDoc,int numDoc,String dir,String name,String tel){
        Connection conex = null;
        try {
            ConexionBD con = new ConexionBD();
            conex = con.getBasicDataSource().getConnection();
            PreparedStatement query = conex.prepareStatement("UPDATE tercero SET tipo_id = ?,numero_id=?,direccion=?,nombre_tercero=?,telefono=? WHERE tercero_id ="+terId);
            query.setString(1, tipoDoc);
            query.setInt(2,(int) numDoc);
            query.setString(3, dir);
            query.setString(4,name);
            query.setString(5, tel);
            query.execute();
            System.out.println("[ProvidersModel]: se actualizo el tercero: " + name);
        } catch (SQLException ex) {
            Logger.getLogger(CustomersModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
          try{ if(conex != null) conex.close();}catch(Exception e){ System.out.println("[ProvidersModel] Error: no fue posible liberar la conexión");}
        }
    }
        
    public void getDataProvider(int numDocProveedor){
       Connection conexion = null;
        try{
            ConexionBD conexionPoll = new ConexionBD();
            conexion = conexionPoll.getBasicDataSource().getConnection();
            PreparedStatement query = conexion.prepareStatement("SELECT * FROM tercero NATURAL JOIN proveedor WHERE numero_id = ?");
            query.setInt(1,numDocProveedor);
            ResultSet response = query.executeQuery();
            while(response.next()){
              this.tercero_id = response.getInt(1);
              this.tipoId = response.getString(2);
              this.numeroDoc = response.getInt(3);
              this.direccion = response.getString(4);
              this.nameProvider = response.getString(5);
              this.telefono = response.getString(6);
              this.estado = response.getBoolean(7);
            }
        }
        catch(SQLException ex){
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
          try{ if(conexion != null) conexion.close();}catch(Exception e){ System.out.println("[ProveedorModel] Error: no fue posible liberar la conexión");}
        }      
    }
   
    public boolean update(String tipoId,int numDoc, String dir,String name,String telefono ){
      boolean saved = false;
      Connection conexion = null;
      try{
        ConexionBD conexionPoll = new ConexionBD();
        conexion = conexionPoll.getBasicDataSource().getConnection();
        PreparedStatement query = conexion.prepareStatement("UPDATE tercero SET tipo_id = ?, numero_id = ?, direccion = ?, nombre_tercero = ?, telefono = ? WHERE numero_id = ?");
        query.setString(1,tipoId);
        query.setInt(2,numDoc);
        query.setString(3,dir);
        query.setString(4,name);
        query.setString(5,telefono);
        int result = query.executeUpdate();
        if( result > 0){
          return true;
        }
        else{
          return false;
        }

      }
      catch(SQLException ex){
          Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
          return false;
      }
      finally{
        try{ if(conexion != null) conexion.close(); }catch(Exception e){ System.out.println("[ProviderModel] Error: no se pudo liberar la conexión:"+e); }
      }
    }
    
    public boolean getStatusProvider(int numDoc){
      Connection conexion = null;
      boolean estado = false;
      try{
            ConexionBD conexionPoll = new ConexionBD();
            conexion = conexionPoll.getBasicDataSource().getConnection();
            PreparedStatement query = conexion.prepareStatement(" SELECT get_proveedor_status(?)");
            query.setInt(1, numDoc);
            ResultSet res = query.executeQuery();
            while(res.next()){
                estado = res.getBoolean(1);  
            }
            return estado;
        } catch (SQLException ex) {
            Logger.getLogger(ProvidersModel.class.getName()).log(Level.SEVERE, null, ex);
            return estado;
        }finally{
            try{ if(conexion != null) conexion.close(); }catch(Exception e){ System.out.println("[ProviderModel] Error: no se pudo liberar la conexión:"+e); }
        }
    }
    
    public void setStatusProvider(int numDoc,boolean estado){
        Connection conexion = null;
        try {
            ConexionBD conexionPoll = new ConexionBD();
            conexion = conexionPoll.getBasicDataSource().getConnection();
            PreparedStatement query = conexion.prepareStatement(" SELECT set_proveedor_status(?,?)");
            query.setInt(1, numDoc);
            query.setBoolean(2, estado);
            query.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProvidersModel.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{ if(conexion != null) conexion.close(); }catch(Exception e){ System.out.println("[ProviderModel] Error: no se pudo liberar la conexión:"+e); }
        }
    }

}
