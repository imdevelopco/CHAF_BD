package controllers;

import models.UserModel;

/**
 *
 * @author camilo
 */
public final class CurrentSesionController {

    private int id;
    private String name;
    private String rol;

    public CurrentSesionController(int id) {
        UserModel user = new UserModel();
        user.setUser(id);
        this.name = user.getNombre();
        this.id = user.getTercero_id();
    }

    public String getName() {
        return name;
    }

    public String getRol() {
        
        return rol;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setRol(String userRol) {
        this.rol = userRol;
    }

}
