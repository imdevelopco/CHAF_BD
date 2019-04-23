/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
/**
 *
 * @author Carlos Andres
 */
public class ConexionBD {

    private static DataSource dataSource = null;
    private BasicDataSource basicDataSource=null;

    public void inicializaDataSource(){
     System.out.println("[ConexionDB] Inicializar basicDataSource");
     BasicDataSource basicDataSource = new BasicDataSource();
     basicDataSource.setDriverClassName("org.postgresql.Driver");
     basicDataSource.setUsername("chaf");
     basicDataSource.setPassword("Cali20*Q");
     basicDataSource.setUrl("jdbc:postgresql://chaf-pruebas.cjbpeuvptazq.us-east-2.rds.amazonaws.com:5432/CHAF_PRUEBA");
     System.out.println("[ConexionDB] "+basicDataSource);
     dataSource = basicDataSource;
    }


    public DataSource getBasicDataSource(){
      if(this.dataSource==null){
          inicializaDataSource();
      }
      System.out.println("[ConexionDB] getDatasource: "+ dataSource);
      BasicDataSource bds = (BasicDataSource) dataSource;
      System.out.println("[ConexionDB] NumActive: " + bds.getNumActive());
      System.out.println("[ConexionDB] NumIdle: " + bds.getNumIdle());
      return dataSource;
    }
}
