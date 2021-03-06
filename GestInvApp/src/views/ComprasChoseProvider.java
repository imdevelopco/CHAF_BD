/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.ProvidersController;
import controllers.ComprasController;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author camilo
 */
public class ComprasChoseProvider extends javax.swing.JFrame {

    private String input;
    private ComprasController compras = null;
    private ArrayList<ArrayList> proveedores;
    private Object id = null;
    private CHAFDependenciesViews dp = new CHAFDependenciesViews();
    private ComprasView comprasView;

    /**
     * Creates new form VentasChoseClient
     */
    public ComprasChoseProvider() {
        initComponents();
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        setSize(width/2, height/2);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setComprasView(ComprasView comprasView){
      this.comprasView = comprasView;
    }

    public void setProvidersTable(ArrayList<ArrayList> proveedores){
      this.proveedores = proveedores;
      Object[][] tabla = new Object[proveedores.size()][5];
      int j = 0;
      for (int i = 0; i < proveedores.size() ; i++ ) {
        tabla[j][0] = proveedores.get(i).get(0).toString();
        tabla[j][1] = proveedores.get(i).get(4).toString();
        tabla[j][2] = proveedores.get(i).get(2).toString();
        tabla[j][3] = proveedores.get(i).get(3).toString();
        tabla[j][4] = proveedores.get(i).get(5).toString();
        j++;
      }
      this.setModelTable(tabla);
    }

    public void setModelTable(Object[][] tabla){
      DefaultTableModel model = new DefaultTableModel(
        tabla,
        new String[]{
          "Id","Nombre","NumeroId","Dirección","Telefono"
        }
      ){
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };

      jTable1.setModel(model);
    }

    public int getId(){
      int idreturn = this.id == null ? -1 : Integer.parseInt(this.id.toString());
      return idreturn;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        input1 = new views.Input();
        nombreProveedor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        materialButton1 = new libraries.MaterialButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        nombreProveedor.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        nombreProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handlerInput(evt);
            }
        });
        nombreProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                keyTypedEvent(evt);
            }
        });

        javax.swing.GroupLayout input1Layout = new javax.swing.GroupLayout(input1);
        input1.setLayout(input1Layout);
        input1Layout.setHorizontalGroup(
            input1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(input1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        input1Layout.setVerticalGroup(
            input1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, input1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouseClikedOnTable(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        materialButton1.setBackground(new java.awt.Color(119, 177, 236));
        materialButton1.setText("Seleccionar proveedor");
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(input1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(materialButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(input1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(materialButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void materialButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materialButton1ActionPerformed
        // TODO add your handling code here:
        if(id == null){
          JOptionPane.showMessageDialog(
                          this,
                          "debes elegir un proveedor primero",
                          "Advertencia", JOptionPane.INFORMATION_MESSAGE,
                          dp.getChafLogo());
        }
        else{
          if(comprasView != null) comprasView.setIdProvider(Integer.parseInt(id.toString()));
          if(comprasView != null) comprasView.setInfoProvider();
          this.dispose();
        }
    }//GEN-LAST:event_materialButton1ActionPerformed

    private void handlerInput(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_handlerInput
        // TODO add your handling code here:

    }//GEN-LAST:event_handlerInput

    private void keyTypedEvent(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyTypedEvent
        // TODO add your handling code here:
        input = nombreProveedor.getText();
          if(compras == null) compras = new ComprasController();
          this.setProvidersTable(compras.getProveedoresWhereName(input));

    }//GEN-LAST:event_keyTypedEvent

    private void mouseClikedOnTable(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseClikedOnTable
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        int col = jTable1.getSelectedColumn();
        id = jTable1.getModel().getValueAt(row, 2);
         //JOptionPane.showMessageDialog(this,id.toString(),"Advertencia", JOptionPane.INFORMATION_MESSAGE,dp.getChafLogo());
    }//GEN-LAST:event_mouseClikedOnTable

    /**
   * @param args the command line arguments
   */
      public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(ComprasChoseProvider.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ComprasChoseProvider.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ComprasChoseProvider.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ComprasChoseProvider.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ComprasChoseProvider().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private views.Input input1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private libraries.MaterialButton materialButton1;
    private javax.swing.JTextField nombreProveedor;
    // End of variables declaration//GEN-END:variables
}
