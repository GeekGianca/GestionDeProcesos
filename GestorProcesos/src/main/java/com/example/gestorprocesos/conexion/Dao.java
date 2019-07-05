/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.gestorprocesos.conexion;

import java.util.List;

/**
 *
 * @author Gianc
 */
public interface Dao {
    boolean insert(Proceso proceso);
    Proceso select(int idproceso);
    boolean update(Proceso proceso);
    boolean delete(int idproceso);
    List<Proceso> listProceso();
}
