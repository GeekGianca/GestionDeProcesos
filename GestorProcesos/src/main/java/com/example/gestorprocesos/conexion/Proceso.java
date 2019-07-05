/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.gestorprocesos.conexion;

/**
 *
 * @author Gianc
 */
public class Proceso {
    private Integer pid;
    private String nombre;
    private String usuario;
    private String descripcion;
    private int prioridad;

    public Proceso() {
    }

    public Proceso(Integer pid) {
        this.pid = pid;
    }

    public Proceso(Integer pid, String nombre, String usuario, String descripcion, int prioridad) {
        this.pid = pid;
        this.nombre = nombre;
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pid != null ? pid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proceso)) {
            return false;
        }
        Proceso other = (Proceso) object;
        if ((this.pid == null && other.pid != null) || (this.pid != null && !this.pid.equals(other.pid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "appweb.Proceso[ pid=" + pid + " ]";
    }
}
