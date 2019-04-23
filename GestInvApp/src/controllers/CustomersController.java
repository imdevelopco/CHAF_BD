/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.CustomersModel;
import java.util.ArrayList;


/**
 *
 * @author CArlos Andres Cordoba
 */
public class CustomersController {
    
    private CustomersModel modeloCustomer = new CustomersModel();
    private ArrayList clientes;
    
    public String getClientsForTable(int tipoDato,int iterador){
        //tipo dato:
        // 0 = tipo documento cliente
        // 1 = numero de documento del cliente
        // 2 = nombre del cliente
        clientes = this.modeloCustomer.getUsersExist();
        ArrayList datos = (ArrayList) clientes.get(iterador);
        return (String) datos.get(tipoDato);
    }
    public int getUsersOnTable(){
        return this.modeloCustomer.getCantidadClientes();
    }
    public void insertUser(String tipoDoc,int numDoc,String dir,String name,String tel){
         this.modeloCustomer.insertarCliente(tipoDoc, numDoc, dir, name, tel);
    }
    public void updateUser(String tipoDoc,int numDoc,String dir,String name,String tel){
        this.modeloCustomer.actualizarCliente(Integer.parseInt(this.showRegisterCustomer(numDoc).get(0)),tipoDoc, numDoc, dir, name, tel);
    }
    
    public ArrayList<String> showRegisterCustomer(int numDocProv){
        ArrayList <String> dataProvider = new ArrayList<String>();
        this.modeloCustomer.getDataCliente(numDocProv);
        //tercero id
        dataProvider.add(String.valueOf(this.modeloCustomer.getTercero_id()));    
        // numero documento
        dataProvider.add(String.valueOf(this.modeloCustomer.getNumeroDoc()));
        // direccion
        dataProvider.add(this.modeloCustomer.getDireccion());
        // nombre
        dataProvider.add(this.modeloCustomer.getNameCliente());
        //telefono
        dataProvider.add(this.modeloCustomer.getTelefono());
        return dataProvider;
    }
    public boolean getEstadoCliente(int numDoc){
        return this.modeloCustomer.getStatusCustomer(numDoc);
    }
    public boolean cambiarEstado(int numDoc,boolean nuevoEstado){
        if(this.modeloCustomer.getStatusCustomer(numDoc) != nuevoEstado){
            this.modeloCustomer.setStatusCustomer(numDoc, nuevoEstado);
            return true;
        }else{
            return false;
        }
    }
}
