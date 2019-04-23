/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import controllers.CurrentSesionController;
import controllers.ProductsController;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author camilo
 */
public class ProductsView extends javax.swing.JFrame {

    private Date date = Calendar.getInstance().getTime();
    private DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
    private String fechaActual = dateFormat.format(date);
    private int userIdLogged; //id del usuario logueado
    private LabelRenderer renderizador = new LabelRenderer();
    private int sizeColumn = 40;
    private ProductsController productCtl = null;
    private JButton editar, eliminar, activar;
    private ImageIcon editarIcon,eliminarIcon,activarIcon;
    private Icon edImg,elimImg,actImg;
    private ArrayList<ArrayList> productos;

    /**
     * Creates new form ProductsView
     */
    public ProductsView() {

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

         initComponents();
         this.setResizable(false);
    }

    public void setUserIdLogged(int id){
      this.userIdLogged = id;
    }
    
    public void setCurrentUserName(String name){
       this.nameUser.setText(name);
    }
    
    public void setCurrentUserRol(String rol){
        this.rolUser.setText(rol);
    }
    

    public void setProductsTable(ArrayList<ArrayList> productos){
      this.productos = productos;
      Object[][] tabla = new Object[productos.size()][9];
      int j = 0;
      for (int i = 0; i < productos.size() ; i++ ) {
        tabla[j][0] = productos.get(i).get(0).toString();
        tabla[j][1] = productos.get(i).get(1).toString();
        tabla[j][2] = productos.get(i).get(4).toString();
        tabla[j][3] = productos.get(i).get(2).toString();
        tabla[j][4] = productos.get(i).get(3).toString();
        tabla[j][5] = productos.get(i).get(5).toString();
        tabla[j][6] = productos.get(i).get(6).toString();
        tabla[j][7] = this.editar;
        tabla[j][8] = productos.get(i).get(7).toString().equals("A") ? this.eliminar : this.activar;
        j++;
      }
      this.setModelTable(tabla);
    }

    public void updateTableEditEstatus(int row, String type){
      Object[][] tabla = new Object[productos.size()][9];
      int j = 0;
      System.out.println("[DEBUG] row:"+row);
      for (int i = 0; i < productos.size() ; i++ ) {
        tabla[j][0] = productos.get(i).get(0).toString();
        tabla[j][1] = productos.get(i).get(1).toString();
        tabla[j][2] = productos.get(i).get(4).toString();
        tabla[j][3] = productos.get(i).get(2).toString();
        tabla[j][4] = productos.get(i).get(3).toString();
        tabla[j][5] = productos.get(i).get(5).toString();
        tabla[j][6] = productos.get(i).get(6).toString();
        tabla[j][7] = this.editar;
        if(row == i){
          String status = type.equals("showBtnDelete") ? "A": "I";
          this.updateProductos(productos.get(i), status, i);
          tabla[j][8] = type.equals("showBtnDelete") ? this.eliminar : this.activar;
        }
        else{
          tabla[j][8] = productos.get(i).get(7).toString().equals("A") ? this.eliminar : this.activar;
        }
        j++;
      }
      this.setModelTable(tabla);
    }

    public void setModelTable(Object[][] tabla){
      DefaultTableModel model = new DefaultTableModel(
        tabla,
        new String[]{
          "Id","Nombre","Marca","Precio Compra","Precio Venta","Proveedor","Cantidad","Editar","Desactivar/Activar"
        }
      ){
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };

      jTable1.setModel(model);
      this.renderTable();
    }

    public void updateProductos(ArrayList<String> product, String status, int index){
      ArrayList<String> producto = new ArrayList<String>();
      producto.add(product.get(0).toString());
      producto.add(product.get(1).toString());
      producto.add(product.get(2).toString());
      producto.add(product.get(3).toString());
      producto.add(product.get(4).toString());
      producto.add(product.get(5).toString());
      producto.add(product.get(6).toString());
      producto.add(status);
      this.productos.set(index, producto);
    }

    public void renderTable(){
      this.jTable1.getColumn("Editar").setCellRenderer(renderizador);
      this.jTable1.getColumn("Editar").setMaxWidth(sizeColumn);
      this.jTable1.getColumn("Desactivar/Activar").setCellRenderer(renderizador);
      this.jTable1.getColumn("Desactivar/Activar").setMaxWidth(sizeColumn + 20);
      this.jTable1.setRowHeight(30);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nameUser = new javax.swing.JLabel();
        rolUser = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        materialButton1 = new libraries.MaterialButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText(this.fechaActual);

        nameUser.setText("Nombre usuario");

        rolUser.setText("Rol");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Opciones", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouseClicledTB(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        materialButton1.setBackground(new java.awt.Color(119, 177, 236));
        materialButton1.setText("REGISTRAR PRODUCTO");
        materialButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                materialButton1MouseClicked(evt);
            }
        });
        materialButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materialButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(nameUser)
                            .addComponent(rolUser)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(materialButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rolUser)
                .addGap(11, 11, 11)
                .addComponent(materialButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegesterActionPerformed
        // TODO add your handling code here:
        if(productCtl == null) productCtl = new ProductsController();
        productCtl.setUserIdLogged(this.userIdLogged);
        productCtl.showFormRegister();
    }//GEN-LAST:event_btnRegesterActionPerformed

    private void mouseClicledTB(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseClicledTB
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        int col = jTable1.getSelectedColumn();

        if(col == 7){
         ProductsController productCtl = new ProductsController();
         Object id = jTable1.getModel().getValueAt(row, 0);
         productCtl.showFormEdit(id.toString());
        }

        if(col == 8){
          int seleccion;
          String id = jTable1.getModel().getValueAt(row, 0).toString();
          Object typeButton = jTable1.getModel().getValueAt(row, 8);

          if(productCtl == null) productCtl = new ProductsController();
          if(typeButton.toString().substring(20, 27).equals("elimina")){
            seleccion = JOptionPane.showConfirmDialog(null, "¿Desea desactivar el producto?", "Desactivar producto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(seleccion == 0){
              ArrayList<String> result = productCtl.delete(Integer.parseInt(id));
              if(result.get(0).equals("true")) this.updateTableEditEstatus(row, "showBtnActivate");
              JOptionPane.showMessageDialog(null,result.get(1) );
            }
          }
          else{
            seleccion = JOptionPane.showConfirmDialog(null, "¿Desea activar el producto?", "Activar producto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(seleccion == 0){
              ArrayList<String> result = productCtl.activate(Integer.parseInt(id));
              if(result.get(0).equals("true")) this.updateTableEditEstatus(row, "showBtnDelete");
              JOptionPane.showMessageDialog(null,result.get(1) );
            }
          }

        }
    }//GEN-LAST:event_mouseClicledTB

    private void materialButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_materialButton1MouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_materialButton1MouseClicked

    private void materialButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materialButton1ActionPerformed
        // TODO add your handling code here:
        if(productCtl == null) productCtl = new ProductsController();
        productCtl.setUserIdLogged(this.userIdLogged);
        productCtl.showFormRegister();
    }//GEN-LAST:event_materialButton1ActionPerformed

    public class LabelRenderer extends DefaultTableCellRenderer implements TableCellRenderer{
     public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus,int row,int column){
      return (Component)value;
     }
    }

    /**
     * @param args the command line arguments
     */
    public void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProductsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductsView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private libraries.MaterialButton materialButton1;
    private javax.swing.JLabel nameUser;
    private javax.swing.JLabel rolUser;
    // End of variables declaration//GEN-END:variables
}
