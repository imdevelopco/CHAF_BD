/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import models.ProvidersModel;
import views.ComprasView;

/**
 *
 * @author camilo
 */
public class ComprasController {

    private ProvidersModel proveedor = null;

    public void showComprasView(){
        ComprasView comprasView = new ComprasView();
        comprasView.setVisible(true);
    }

    public ArrayList<ArrayList> getProveedoresWhereName(String name){
      if(proveedor == null) proveedor = new ProvidersModel();
      return proveedor.getProviderWhere(name);
    }
}
