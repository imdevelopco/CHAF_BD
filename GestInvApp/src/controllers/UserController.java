/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import models.UserModel;

/**
 *
 * @author Carlos Andres
 */
public class UserController {
    private UserModel modeloUser = new UserModel();
    private ArrayList usuarios;
    
    public void insertUser(String tipoDoc,int numDoc,String dir,String name,String tel,String login,String pwd){

        //faltan validaciones
        this.modeloUser.insertUser(tipoDoc, numDoc, dir, name,tel,login,pwd);
        
    }
    
    public String getRol(String login){
        return this.modeloUser.getRoleUser(login);        
    }
    
    public String getUserForTable(int tipoDato,int iterador){
       //tipo dato:
       // 0 = login del usuario
       // 1 = nombre del usuario 
       usuarios = this.modeloUser.getUsersExist();
       ArrayList datos = (ArrayList) usuarios.get(iterador);
       return (String) datos.get(tipoDato);
    }
    
    public int getUsersOnTable(){
        return modeloUser.getCantidadUsers();
    }
    public int getTerceroIdUser(){
        return this.modeloUser.getTercero_id();
    }
    public String getNameUser(String login){
        this.modeloUser.whereUserName(login);
        return this.modeloUser.getNombre();
    }
    public void actualizarDatos(String tipoDoc,int numDoc,String dir,String name,String tel,String login,String pwd){
      this.modeloUser.updateUser(tipoDoc, numDoc, dir, name, tel, login, pwd);
    }
    
    public ArrayList<String> showRegisterUser(String login){
     ArrayList <String> dataProvider = new ArrayList<String>();
        this.modeloUser.whereUserName(login);
        //tercero id
        dataProvider.add(String.valueOf(this.modeloUser.getTercero_id()));    
        // numero documento
        dataProvider.add(String.valueOf(this.modeloUser.getNumero_id()));
        // direccion
        dataProvider.add(this.modeloUser.getDireccion());
        // nombre
        dataProvider.add(this.modeloUser.getNombre());
        //telefono
        dataProvider.add(this.modeloUser.getTelefono());
        return dataProvider;
    }
    public boolean getEstadoUser(String login){
        return this.modeloUser.getStatusUser(login);
    }
    public boolean cambiarEstado(String login,boolean nuevoEstado){
        if(this.modeloUser.getStatusUser(login) != nuevoEstado){
            this.modeloUser.setStatusUser(login, nuevoEstado);
            return true;
        }else{
            return false;
        }
    }

}
