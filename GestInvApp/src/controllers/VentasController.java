/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import models.CustomersModel;
import views.VentasView;

/**
 *
 * @author camilo
 */
public class VentasController {

    private CustomersModel cliente = null;

    public void showVentasView(){
        VentasView ventasView = new VentasView();
        ventasView.setVisible(true);
    }

    public ArrayList<ArrayList> getClientesWhereName(String name){
      if(cliente == null) cliente = new CustomersModel();
      return cliente.getClientWhere(name);
    }
}
