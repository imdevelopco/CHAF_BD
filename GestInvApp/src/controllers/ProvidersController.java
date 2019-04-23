/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import models.CustomersModel;
import models.ProvidersModel;

/**
 *
 * @author Carlos Andres Cordoba
 */
public class ProvidersController {
   private ProvidersModel modeloProvider = new ProvidersModel();
    private ArrayList proveedores;
    
    public String getProvidersForTable(int tipoDato,int iterador){
        //tipo dato:
        // 1 = tipo documento proveedor
        // 2 = numero de documento del proveedor
        // 4 = nombre del proveedor
        proveedores = this.modeloProvider.getProveedores();
        ArrayList datos = (ArrayList) proveedores.get(iterador);
        return (String) datos.get(tipoDato);
    }
    public int getUsersOnTable(){
        return this.modeloProvider.getCantProveedores();
    }
    
    public void insertUser(String tipoDoc,int numDoc,String dir,String name,String tel){
        this.modeloProvider.insertarProveedor(tipoDoc, numDoc, dir, name, tel);
    }
    
    public void updateUser(String tipoDoc,int numDoc,String dir,String name,String tel){
        this.modeloProvider.actualizarProveedor(Integer.parseInt(this.showRegisterProvider(numDoc).get(0)),tipoDoc, numDoc, dir, name, tel);
    }
    
    public ArrayList<String> showRegisterProvider(int numDocProv){
        ArrayList <String> dataProvider = new ArrayList<String>();
        this.modeloProvider.getDataProvider(numDocProv);
        //tercero id
        dataProvider.add(String.valueOf(this.modeloProvider.getTercero_id()));    
        // numero documento
        dataProvider.add(String.valueOf(this.modeloProvider.getNumeroDoc()));
        // direccion
        dataProvider.add(this.modeloProvider.getDireccion());
        // nombre
        dataProvider.add(this.modeloProvider.getNameProvider());
        //telefono
        dataProvider.add(this.modeloProvider.getTelefono());
        return dataProvider;
    }
    public boolean getEstadoProveedor(int numDoc){
        return this.modeloProvider.getStatusProvider(numDoc);
    }
    public boolean cambiarEstado(int numDoc,boolean nuevoEstado){
        if(this.modeloProvider.getStatusProvider(numDoc) != nuevoEstado){
            this.modeloProvider.setStatusProvider(numDoc, nuevoEstado);
            return true;
        }else{
            return false;
        }
    }
}
