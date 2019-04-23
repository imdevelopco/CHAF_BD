package controllers;

import models.LoginModel;
import models.UserModel;
import controllers.CurrentSesionController;
/**
* @fileoverview Controlador del login
* @version 1.0
*
* @author Camilo Arias <CamiloArias47>
* @copyright
* History
* v1.0 La primera versión de aprMenu fue escrita por Camilo Arias
*/
public class LoginController {

    private boolean logged = false; //indica si el logueo fue satisfactorio
    private int idUserLogged; //almacena el id del usuario logueado

    public LoginController(){

    }

    public void clickLogin(String user, String pass){
        LoginModel login = new LoginModel();
        login.setUser(user);
        login.setPass(pass);
        if(login.validate()){
          System.out.println("[LoginController] Access allow");
          setCurrentUser(user); //llamar al metodo que establece el usuario logueado
          logged = true;
        }
        else{
          System.out.println("[LoginController] Access deny");
          logged = false;
        }
    }

    /*
    *Consulta el id del usuario logueado
    **/
    private void setCurrentUser(String userName){
      UserModel user = new UserModel();
      user.whereUserName(userName);
      this.idUserLogged = user.getTercero_id();
    }

    public boolean getLogged(){
      return logged;
    }

    public int getIdUserLogged(){
      return idUserLogged;
    }
}
