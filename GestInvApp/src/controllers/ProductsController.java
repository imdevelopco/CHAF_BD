/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.ProvidersModel;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.MarcaModel;
import views.ProductsView;
import views.ProductsRegisterView;
import models.ProductModel;

/**
 *
 * @author camilo
 */
public class ProductsController {

  private int userIdLogged; //id del usuario logueado
  private ProductModel product = null;
  private JButton editar,eliminar,activar;
  private ImageIcon editarIcon,eliminarIcon,activarIcon;
  private Icon edImg,elimImg,actImg;
  private ProductsView productView = null;
  //private Object tabla[][];
  private ProductsRegisterView formRegister = null;
  private ProvidersModel proveedor = null;
  private MarcaModel marca = null;

  public ProductsController(){

  }

  public void setUserIdLogged(int id){
    this.userIdLogged = id;
  }

  /*
  *Muestra el modulo de productos
  **/
  public void showView(String nameUser,String rolUser){
    if(productView == null) productView = new ProductsView();
    productView.setUserIdLogged(this.userIdLogged);
    productView.setCurrentUserName(nameUser);
    productView.setCurrentUserRol(rolUser);
    productView.setVisible(true);
    productView.setLayout(null);
    this.setButons(); //establecemos los botones de la tabla
    productView.setProductsTable(this.setTabla());
    productView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  /*
  *Muestra el formulario de registro
  **/
  public void showFormRegister(){
    if(formRegister == null) formRegister = new ProductsRegisterView();
    formRegister.setInfoUser();
    formRegister.setComboBoxProveedores(this.getComboBoxProveedores());
    formRegister.setComboBoxBrands(this.getComboBoxMarcas());
    formRegister.setVisible(true);
    formRegister.setLayout(null);
    formRegister.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  /*
  *Muestra el formulario para editar un producto
  *@param {String} id producto_id del producto a editar
  **/
  public void showFormEdit(String id){
    if(product == null) product = new ProductModel(id);

    if(formRegister == null) formRegister = new ProductsRegisterView();
    formRegister.setTipeAction("edit");
    formRegister.setInfoUser();
    formRegister.setComboBoxBrands(this.getComboBoxMarcas());
    formRegister.setComboBoxProveedores(this.getComboBoxProveedores());
    formRegister.setData(id, product.getDescripcion(), product.getCosto(), product.getPrecio_venta(), product.getCantidad(), product.getNombreProveedor(), product.getNombreMarca());
    formRegister.setVisible(true);
    formRegister.setLayout(null);
    formRegister.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  /*
  * obtiene un ArrayList con los proveedores y los convierte en un modelo para mostrar en el combobox
  **/
  public DefaultComboBoxModel getComboBoxProveedores(){
    DefaultComboBoxModel model = new DefaultComboBoxModel();
    if(proveedor == null) proveedor = new ProvidersModel();
    ArrayList<ArrayList> proveedores = proveedor.getProveedores();
    for (int i = 0;i < proveedores.size() ;i++ ) {
        model.addElement(proveedores.get(i).get(4));
    }
    return model;
  }

  public DefaultComboBoxModel getComboBoxMarcas(){
    DefaultComboBoxModel model = new DefaultComboBoxModel();
    if(marca == null) marca = new MarcaModel();
    ArrayList<ArrayList> marcas = marca.getBrands();
    for (int i = 0;i < marcas.size() ;i++ ) {
        model.addElement(marcas.get(i).get(1));
    }
    model.addElement("otra");
    return model;
  }

  private void setButons(){
     //Creacion de boton editar
    editar          = new JButton();
    editar.setBackground(Color.white);
    editarIcon      = new ImageIcon(getClass().getResource("/img/editar.png"));
    edImg           = new ImageIcon(editarIcon.getImage().getScaledInstance(20, 20, 0));
    editar.setIcon(edImg);
    editar.setName("Editar");


    //Creacion de boton eliminar
    eliminar        = new JButton();
    eliminar.setBackground(Color.white);
    eliminarIcon    = new ImageIcon(getClass().getResource("/img/desactivar.png"));
    elimImg         = new ImageIcon(eliminarIcon.getImage().getScaledInstance(20, 20, 0));
    eliminar.setIcon(elimImg);
    eliminar.setName("eliminar");


    //Creacion de boton activar
    activar         = new JButton();
    activar.setBackground(Color.white);
    activarIcon     = new ImageIcon(getClass().getResource("/img/activar.png"));
    actImg          = new ImageIcon(activarIcon.getImage().getScaledInstance(20, 20, 0));
    activar.setIcon(actImg);
    activar.setName("activar");
  }

  /*
  *Establece el onjeto con los productos
  **/
  public ArrayList<ArrayList> setTabla(){
      if(product == null) product = new ProductModel();
      ArrayList<ArrayList> productos = product.getProducts();
      return productos;
  }


  /*
  *valida wue los campos del registro e producto esten completos
  **/
  public boolean validate(String brand, String name, String priceBuy, String priceSell, String amoung, String provider){
    if(brand.equals("") || name.equals("") || priceBuy.equals("") || priceSell.equals("") || amoung.equals("") ){
      return false;
    }

    return true;
  }


  //::::::::::::::::::::::::::::::::::::::::::::::::::CRUD:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

  /*
  *guarda un producto nuevo
  **/
  public ArrayList<String> save(String brand, String name, String priceBuy, String priceSell, String amoung, String provider){
    ArrayList<String> result = new ArrayList<String>();
    String saved = "false";
    String message = "";

    if(!validate(brand,name,priceBuy,priceSell,amoung,provider)){
      message = "Completa los campos";
    }
    else{
      MarcaModel model = new MarcaModel(brand);
      int idBrand = model.getId();

      ProvidersModel proveedor = new ProvidersModel(provider);
      int idProvider = proveedor.getTercero_id();

      product = new ProductModel();

      if(product.save(idBrand,name,priceBuy,priceSell,Integer.parseInt(amoung),idProvider) ){
        saved = "true";
        message = "Producto guardado";
        JOptionPane.showMessageDialog(null, "Se ha guardado el producto");
      }
    }

    result.add(saved);
    result.add(message);

    return result;
  }

  /*
  *actualiza un producto
  **/
  public ArrayList<String> update(String id, String brand, String name, String priceBuy, String priceSell, String amoung, String provider){
    ArrayList<String> result = new ArrayList<String>();
    String saved = "false";
    String message = "";

    if(!validate(brand,name,priceBuy,priceSell,amoung,provider)){
      message = "Completa los campos";
    }
    else{
      MarcaModel model = new MarcaModel(brand);
      int idBrand = model.getId();

      ProvidersModel proveedor = new ProvidersModel(provider);
      int idProvider = proveedor.getTercero_id();
      if(product == null) product = new ProductModel();

      if(product.update(id,idBrand,name,priceBuy,priceSell,amoung,idProvider)){
        saved = "true";
        message = "Producto guardado";
        JOptionPane.showMessageDialog(null, "Se ha actualizado el producto");
      }

    }

    result.add(saved);
    result.add(message);

    return result;
  }

  /*
  *
  **/
  public ArrayList<String> delete(int id){
    ArrayList<String> result = new ArrayList<String>();
    String deleted = "false";
    String message = "No se eliminó e producto";

    if(product == null) product = new ProductModel();

    if(product.delete(id)){
      deleted = "true";
      message = "Producto eliminado";
    }

    result.add(deleted);
    result.add(message);
    return result;
  }

  public ArrayList<String> activate(int id){
    ArrayList<String> result = new ArrayList<String>();
    String activated = "false";
    String message = "No se eliminó e producto";

    if(product == null) product = new ProductModel();

    if(product.restore(id)){
      activated = "true";
      message = "Producto activado";
    }

    result.add(activated);
    result.add(message);
    return result;
  }

  public boolean saveBrand(String brandName){
    boolean saved = false;
    MarcaModel marca = new MarcaModel();
    if(marca.save(brandName)){
      saved = true;
    }
    return saved;
  }

}
