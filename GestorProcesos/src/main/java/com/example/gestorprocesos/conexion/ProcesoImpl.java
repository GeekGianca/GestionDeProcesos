/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.gestorprocesos.conexion;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gianc
 */
public class ProcesoImpl implements Dao {

    private QueryTranfers dao;

    @Override
    public boolean insert(Proceso proceso) {
        dao = new QueryTranfers();
        boolean isSave = false;
        try {
            String sql = String.format("INSERT INTO `Proceso`(`pid`, `nombre`, `usuario`, `descripcion`, `prioridad`) VALUES (%s,'%s',%s)", proceso.getPid(), proceso.getNombre(), proceso.getUsuario(), proceso.getDescripcion(), proceso.getPrioridad());
            isSave = dao.execute(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            dao.close();
        }
        return isSave;
    }

    @Override
    public Proceso select(int idproceso) {
        return null;
    }

    @Override
    public boolean update(Proceso proceso) {
        return false;
    }

    @Override
    public boolean delete(int idproceso) {
        return false;
    }

    @Override
    public List<Proceso> listProceso() {
        dao = new QueryTranfers();
        List<Proceso> procesoList = new ArrayList<>();
        Proceso proceso;
        String sql = "SELECT * FROM `proceso`";
        try {
            ResultSet rs = dao.query(sql);
            while (rs.next()) {
                proceso = new Proceso(
                        rs.getInt("pid"),
                        rs.getString("nombre"),
                        rs.getString("usuario"),
                        rs.getString("descripcion"),
                        rs.getInt("prioridad")
                );
                procesoList.add(proceso);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            dao.close();
        }
        return procesoList;
    }

}
