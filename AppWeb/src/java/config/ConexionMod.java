/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Mayi
 */
public class ConexionMod {
    private static ConexionMod conexion;
    private EntityManagerFactory factory;
    
    private ConexionMod(){
        factory = Persistence.createEntityManagerFactory("AppWebPU");
    }
    
    public static ConexionMod getConexion(){
        if (conexion == null) {
            conexion = new ConexionMod();
        }
        return conexion;
    }

    public EntityManagerFactory getFactory() {
        return factory;
    }
}
