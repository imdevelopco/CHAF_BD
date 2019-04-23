/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Carlos Cordoba Ramos
 */
public class CHAFDependenciesViews {
    /* Botones para JTables*/
    private JButton editar,eliminar,activar;
    private ImageIcon editarIcon,eliminarIcon,activarIcon;
    private Icon edImg,elimImg,actImg;
    /* tamaño de las columnas de editar y activar*/
    private int sizeColumn = 40;
    /*renderizador encargado de "convertir" los datos del jlabel para que se vean las imgns en el jtable  */
    private LabelRenderer renderizador = new LabelRenderer();
    private Dimension DefaultSizaCHAF;
    /*Datos de fecha*/
    private Date date = Calendar.getInstance().getTime();
    private DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
    private String fechaActual = dateFormat.format(date);
    
      /**
         Creacion de boton editar
         */
    public CHAFDependenciesViews(){
        editar          = new JButton();
        editar.setBackground(Color.white);
        editarIcon      = new ImageIcon(getClass().getResource("/img/editar.png"));
        edImg           = new ImageIcon(editarIcon.getImage().getScaledInstance(20, 20, 0));
        editar.setIcon(edImg);
        
        /**
         Creacion de boton eliminar
         */             
        
        eliminar        = new JButton();
        eliminar.setBackground(Color.white);
        eliminarIcon    = new ImageIcon(getClass().getResource("/img/desactivar.png"));
        elimImg         = new ImageIcon(eliminarIcon.getImage().getScaledInstance(20, 20, 0));
        eliminar.setIcon(elimImg);
        
        /**
         Creacion de boton activar
         */
        
        activar         = new JButton();
        activar.setBackground(Color.white);
        activarIcon     = new ImageIcon(getClass().getResource("/img/activar.png"));
        actImg          = new ImageIcon(activarIcon.getImage().getScaledInstance(20, 20, 0));
        activar.setIcon(actImg);
        
    }
    
    public JButton getActivar(){
         return this.activar;
    }
     
    public String getFechaActual(){
         return this.fechaActual;
    }
    public JButton getEditar(){
         return this.editar;
    }
    public JButton getEliminar(){
         return this.eliminar;
    }
     
    public Dimension getDefaultSizaCHAF(){
         return DefaultSizaCHAF;
    }
     public LabelRenderer getRender(){
         return this.renderizador;
    }
     
    public int getSizeColumn(){
         return this.sizeColumn;
    }
    public class LabelRenderer extends DefaultTableCellRenderer implements TableCellRenderer{
        public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus,int row,int column){
            return (Component)value;   
        }
    }
     
    public ImageIcon getChafLogo(){
         ImageIcon icon = new ImageIcon(this.getClass().getResource("../img/chaf.png"));
         Image img = icon.getImage();
         Image newimg = img.getScaledInstance(60, 40,  java.awt.Image.SCALE_SMOOTH);
         ImageIcon chafIcon = new ImageIcon(newimg);
         return chafIcon;
    }
}