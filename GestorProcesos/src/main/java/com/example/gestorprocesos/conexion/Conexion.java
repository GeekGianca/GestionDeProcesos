/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.gestorprocesos.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Gianc
 */
public class Conexion {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "geekprogramador23";
    private static final String URL = "jdbc:mysql://localhost:3306/bdingresos?autoReconnect=true&useSSL=false";
    private static Connection cn;

    public Conexion() {
        this.cn = null;
    }

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            cn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al conectar la base de datos.. ", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return cn;

    }

}
