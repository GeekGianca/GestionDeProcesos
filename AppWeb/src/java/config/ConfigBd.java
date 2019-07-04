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
public class ConfigBd {

    private static ConfigBd config;
    private EntityManagerFactory fabricaConn;

    private ConfigBd() {
        fabricaConn = Persistence.createEntityManagerFactory("AppWebPU");
    }

    public static ConfigBd getConexion() {
        if (config == null) {
            config = new ConfigBd();
        }
        return config;
    }

    public EntityManagerFactory getFabricaConn() {
        return fabricaConn;
    }
}
