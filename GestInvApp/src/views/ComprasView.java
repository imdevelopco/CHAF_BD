/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.ProvidersController;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author camilo
 */
public class ComprasView extends javax.swing.JFrame {

    private CHAFDependenciesViews dp = new CHAFDependenciesViews();
    private int idProvider;

    /**
     * Creates new form VentasView
     */
    public ComprasView() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void refresh(){
        //this.initComponents();
        this.repaint();
    }

    public void setNameProvider(String nuevoValor){
        this.NombreProveedor.setText(nuevoValor);
    }
    /* numero de documento del cliente*/
    public void setIdProvider(String nuevoValor){
        this.IdentificacionProveedor.setText(nuevoValor);
    }
    public void setDirProvider(String nuevoValor){
        this.direccionProveedor.setText(nuevoValor);
    }
    public void setTelProvider(String nuevoValor){
        this.telefonoProveedor.setText(nuevoValor);
    }

    public void setIdProvider(int id){
      this.idProvider = id;
      System.out.println("[DEBUG] recibi id:"+id);
    }

    public void setInfoProvider(){
      ProvidersController ctrlProviders = new ProvidersController();
      ArrayList<String> data = ctrlProviders.showRegisterProvider(this.idProvider);
      //this.setClientId(Integer.parseInt(data.get(0)));
      this.setNameProvider(data.get(3));
      this.setIdProvider(data.get(1));
      this.setDirProvider(data.get(2));
      this.setTelProvider  (data.get(4));
      
      System.out.println(this.idProvider);
      System.out.println(data.get(1));
      System.out.println(data.get(2));
      System.out.println(data.get(4));
      this.refresh();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        NombreProveedor = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        IdentificacionProveedor = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        direccionProveedor = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        telefonoProveedor = new javax.swing.JLabel();
        materialButton1 = new libraries.MaterialButton();
        jPanel2 = new javax.swing.JPanel();
        materialButton2 = new libraries.MaterialButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ventas");

        jPanel1.setBackground(new java.awt.Color(219, 219, 219));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.setForeground(new java.awt.Color(219, 219, 219));
        jPanel1.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(32, 171, 230));
        jLabel1.setText("Proveedor");

        NombreProveedor.setText("Nombre Proveedor");

        jLabel3.setForeground(new java.awt.Color(32, 171, 230));
        jLabel3.setText("Numero Identificacion");

        IdentificacionProveedor.setText("65456464");

        jLabel5.setForeground(new java.awt.Color(32, 171, 230));
        jLabel5.setText("Dirección");

        direccionProveedor.setText("Calle 34 # 455");

        jLabel7.setForeground(new java.awt.Color(32, 171, 230));
        jLabel7.setText("Telefono");

        telefonoProveedor.setText("895552215");

        materialButton1.setBackground(new java.awt.Color(119, 177, 236));
        materialButton1.setText("Buscar");
        materialButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materialButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(materialButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(NombreProveedor)
                    .addComponent(jLabel3)
                    .addComponent(IdentificacionProveedor)
                    .addComponent(jLabel5)
                    .addComponent(direccionProveedor)
                    .addComponent(jLabel7)
                    .addComponent(telefonoProveedor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NombreProveedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(IdentificacionProveedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(direccionProveedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(telefonoProveedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(materialButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(219, 219, 219));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        materialButton2.setBackground(new java.awt.Color(119, 177, 236));
        materialButton2.setText("Agregar producto");
        materialButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProduct(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(materialButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(materialButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(367, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getAccessibleContext().setAccessibleName("Compras");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void materialButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materialButton1ActionPerformed
        // TODO add your handling code here:
        ComprasChoseProvider viewChoseProvider = new ComprasChoseProvider();
        viewChoseProvider.setComprasView(this);

    }//GEN-LAST:event_materialButton1ActionPerformed

    private void addProduct(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProduct
        // TODO add your handling code here:
        VentasChoseProduct viewChoseProduct = new VentasChoseProduct();

    }//GEN-LAST:event_addProduct

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
            java.util.logging.Logger.getLogger(VentasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentasView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IdentificacionProveedor;
    private javax.swing.JLabel NombreProveedor;
    private javax.swing.JLabel direccionProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private libraries.MaterialButton materialButton1;
    private libraries.MaterialButton materialButton2;
    private javax.swing.JLabel telefonoProveedor;
    // End of variables declaration//GEN-END:variables
}
