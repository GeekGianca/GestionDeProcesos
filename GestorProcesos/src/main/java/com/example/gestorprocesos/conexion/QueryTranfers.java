/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.gestorprocesos.conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Gianc
 */
public class QueryTranfers {
    private final Connection connection;

    public QueryTranfers() {
        this.connection = Conexion.getConnection();
    }
    
    /**
     * Dependency Injection
     * @param connection 
     */
    public QueryTranfers(Connection connection) {
        this.connection = connection;
    }

    public ResultSet query(String query) {
        Statement stmnt;
        ResultSet rs = null;
        try {
            stmnt = connection.createStatement();
            rs = stmnt.executeQuery(query);
        } catch (SQLException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    public boolean execute(String query) {
        Statement stm;
        boolean save = false;
        try {
            stm = connection.createStatement();
            stm.executeUpdate(query);
            save = true;
        } catch (SQLException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return save;
    }

    public boolean close() {
        boolean ok = true;
        try {
            connection.close();
        } catch (SQLException | NullPointerException ex) {
            ok = false;
            System.out.println(ex.getMessage());
        }
        return ok;
    }
}
