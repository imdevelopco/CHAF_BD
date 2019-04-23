
package models;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import models.ConexionBD;
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
public class UserModel {

    private String tipo_id, direccion, nombre, telefono;
    private int tercero_id, numero_id;
    private int cantidadUsers;
    private ArrayList<ArrayList> usuarios = new ArrayList<ArrayList>();
    private ConexionBD con;
    private Connection conex;
    private boolean estado;
    private String login;
    private String pwd;

    public UserModel(){
        this.setCantidadUsers();
    }

    //Getters

    public String getTipo_id() {
        return tipo_id;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getTercero_id() {
        return tercero_id;
    }

    public int getNumero_id() {
        return numero_id;
    }

    public String getLogin() {
        return login;
    }

    public String getPwd() {
        return pwd;
    }
    
    
    
    

    public UserModel(String tipo_id, String direccion, String nombre, String telefono, int tercero_id, int numero_id) {
        this.tipo_id = tipo_id;
        this.direccion = direccion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.tercero_id = tercero_id;
        this.numero_id = numero_id;
    }

    /*
    *Obtine un usuario segun su id en la tabla tercero
    *@param {int} id id de el usuario.
    **/
    public void setUser(int id){
      ConexionBD conexionPoll = new ConexionBD();
      Connection conexion = null;

        try {
            conexion = conexionPoll.getBasicDataSource().getConnection();
            Statement query = conexion.createStatement();
            ResultSet response = query.executeQuery("SELECT * FROM tercero WHERE tercero_id = '"+id+"'");

            if(response.next()){
              this.tercero_id = response.getInt(1);
              this.tipo_id = response.getString(2);
              this.numero_id = response.getInt(3);
              this.direccion = response.getString(4);
              this.nombre = response.getString(5);
              this.telefono = response.getString(6);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
          try{ if(conexion != null) conexion.close(); }catch(Exception e){ System.out.println("[UserModel] Error: no se pudo liberar la conexión");}
        }
    }

    public String getRoleUser(String login){
        String rol = null;
        try {
            Connection conex = null;   
            ConexionBD con = new ConexionBD();
            conex = con.getBasicDataSource().getConnection();
            PreparedStatement query = conex.prepareStatement("SELECT obtener_rol(?)");
            query.setString(1, login);
            ResultSet response = query.executeQuery();
            while(response.next()){
                rol = response.getString(1);
            }
            return rol;
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rol;
    }
    /*
    * Busca un usuario en la base de datos segun el campo y el valor que se le pase
    **/
    public void where(String field, String value){

      Connection conexion = null;
      try {
          ConexionBD conexionPoll = new ConexionBD();
          conexion = conexionPoll.getBasicDataSource().getConnection();
          Statement query = conexion.createStatement();
          ResultSet response = query.executeQuery("SELECT * FROM tercero WHERE "+field+" = '"+value+"'");

          if(response.next()){
            this.tercero_id = response.getInt(1);
            this.tipo_id = response.getString(2);
            this.numero_id = response.getInt(3);
            this.direccion = response.getString(4);
            this.nombre = response.getString(5);
            this.telefono = response.getString(6);
          }

      } catch (SQLException ex) {
          Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
      }
      finally{
        try{ if(conexion != null) conexion.close();}catch(Exception e){ System.out.println("[UserModel] Error: no fue posible liberar la conexión");}
      }
    }

    /*
    * Busca un usuario en la base de datos segun el campo y el valor que se le pase
    **/
    public void whereUserName(String value){

      ConexionBD conexionPoll = new ConexionBD();
      Connection conexion = null;
      try {
          conexion = conexionPoll.getBasicDataSource().getConnection();
          Statement query = conexion.createStatement();
          ResultSet response = query.executeQuery("SELECT t.tercero_id, t.tipo_id, t.numero_id, t.direccion, t.nombre_tercero, t.telefono, u.login_usuario FROM tercero as t INNER JOIN usuario as u ON u.tercero_id = t.tercero_id WHERE u.login_usuario = '"+value+"'");
          if(response.next()){
            this.tercero_id = response.getInt(1);
            this.tipo_id = response.getString(2);
            this.numero_id = response.getInt(3);
            this.direccion = response.getString(4);
            this.nombre = response.getString(5);
            this.telefono = response.getString(6);
          }

      } catch (SQLException ex) {
          Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
      }
      finally{
        try{ if (conexion != null) conexion.close();}catch(Exception e) { System.out.println("[UserModel] Error: error liberando conexión");}
      }
    }

    /**
     * @author: Carlos Andres Cordoba Ramos
     * Descripcion: Inserta un usuario en la tabla usuario y en la tabla tercero
     * utilizando una funcion definida en la BD.
     */
    public void insertUser(String tipoDoc,int numDoc,String dir,String name,String tel,String login,String pwd){

        Connection conex = null;
        try {
            ConexionBD con = new ConexionBD();
            conex = con.getBasicDataSource().getConnection();
            PreparedStatement query = conex.prepareStatement("SELECT insertar_usuario(?,?,?,?,?,?,?)");
            query.setString(1, tipoDoc);
            query.setInt(2,numDoc);
            query.setString(3, dir);
            query.setString(4,name);
            query.setString(5, tel);
            query.setString(6, login);
            byte[] bytesOfMessage;
            bytesOfMessage = pwd.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(bytesOfMessage);
            pwd = String.valueOf(thedigest);        
            query.setString(7, pwd);
            query.execute();
            System.out.println("[UserModel]: se inserto el tercero: " + name);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);   
        }
        finally{
          try{ if (conex != null) conex.close();}catch(Exception e) { System.out.println("[UserModel] Error: error liberando conexión");}
        }
    }

    /*
    @author Carlos Andres Cordoba
    Metodo que devuelve login y nombre de un usuario que existe en la
    tabla Usuario
    */
    public ArrayList getUsersExist(){
      Connection conexion = null;
      try{
        ConexionBD conexionPoll = new ConexionBD();
        conexion = conexionPoll.getBasicDataSource().getConnection();
        Statement query = conexion.createStatement();
        ResultSet response = query.executeQuery("SELECT u.login_usuario,t.nombre_tercero AS nombre FROM tercero AS t \n" +
                                                "NATURAL JOIN usuario AS u");
        while(response.next()){
            int i= 1;
            ArrayList<String> usuario = new ArrayList<String>();
            while(i<3){
                usuario.add(response.getString(i));
                i++;
            }
            this.usuarios.add(usuario);
        }
        conexion.close();
        return usuarios;
      }
      catch(SQLException ex){
          Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
          return usuarios;
      }finally{
        try{ if(conexion != null) conexion.close(); }catch(Exception e){ System.out.println("[ProductModel] Error: no fue posible liberar la conexión "+e); }
      }
    }

    public int getCantidadUsers(){
       return this.cantidadUsers;
    }

    private void setCantidadUsers() {
      Connection conexion = null;
        try {
          ConexionBD con = new ConexionBD();
          conex = con.getBasicDataSource().getConnection();
          Statement query = conex.createStatement();
          ResultSet response = query.executeQuery("SELECT u.login_usuario,t.nombre_tercero AS nombre FROM tercero AS t \n" +
                  "NATURAL JOIN usuario AS u");
          while(response.next()){
              this.cantidadUsers++;
          }
      } catch (SQLException ex) {
          Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
      }
      finally{
        try{ if(conex != null) conex.close(); }catch(Exception e){ System.out.println("[UserModel] Error: no fue posible liberar la conexión "+e); }
      }
    }
    
    public void updateUser(String tipoDoc,int numDoc,String dir,String name,String tel,String login,String pwd){
        Connection conex = null;
        try {
            ConexionBD con = new ConexionBD();
            conex = con.getBasicDataSource().getConnection();
            PreparedStatement query = conex.prepareStatement("SELECT update_usuario(?,?,?,?,?,?,?)");
            query.setString(1, tipoDoc);
            query.setInt(2,(int) numDoc);
            query.setString(3, dir);
            query.setString(4,name);
            query.setString(5, tel);
            query.setString(6, login);
            byte[] bytesOfMessage;
            bytesOfMessage = pwd.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(bytesOfMessage);
            pwd = String.valueOf(thedigest);        
            query.setString(7, pwd);
            query.execute();
            System.out.println("[UserModel]: se actualizo el tercero: " + name);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);   
        }
        finally{
          try{ if (conex != null) conex.close();}catch(Exception e) { System.out.println("[UserModel] Error: error liberando conexión");}
        }
    }  
    public boolean getStatusUser(String login){
      Connection conexion = null;
      boolean estado = false;
      try{
            ConexionBD conexionPoll = new ConexionBD();
            conexion = conexionPoll.getBasicDataSource().getConnection();
            PreparedStatement query = conexion.prepareStatement(" SELECT get_user_status(?)");
            query.setString(1, login);
            ResultSet res = query.executeQuery();
            while(res.next()){
                estado = res.getBoolean(1);  
            }
            return estado;
        } catch (SQLException ex) {
            Logger.getLogger(ProvidersModel.class.getName()).log(Level.SEVERE, null, ex);
            return estado;
        }finally{
            try{ if(conexion != null) conexion.close(); }catch(Exception e){ System.out.println("[UserModel] Error: no se pudo liberar la conexión:"+e); }
        }
    }
    
    public void setStatusUser(String login,boolean estado){
        Connection conexion = null;
        try {
            ConexionBD conexionPoll = new ConexionBD();
            conexion = conexionPoll.getBasicDataSource().getConnection();
            PreparedStatement query = conexion.prepareStatement(" SELECT set_user_status(?,?)");
            query.setString(1, login);
            query.setBoolean(2, estado);
            query.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProvidersModel.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{ if(conexion != null) conexion.close(); }catch(Exception e){ System.out.println("[UserModel] Error: no se pudo liberar la conexión:"+e); }
        }
    }

}
