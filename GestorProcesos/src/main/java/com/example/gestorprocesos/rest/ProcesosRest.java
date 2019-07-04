/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.gestorprocesos.rest;

import com.example.gestorprocesos.modelo.ListaProcesos;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Geek-Programmer
 */
public class ProcesosRest {
    private WebResource webResource;
    private Client client;
    
    private final String BASE_URL = "http://localhost:8080/RestWeb/";

    public ProcesosRest() {
        client = Client.create(new DefaultClientConfig());
        webResource = client.resource(BASE_URL).path("webresources/");
    }
    
    public ListaProcesos findAll(){
        WebResource wr = webResource;
        return wr.path("com.servicio.modelo.proceso").accept(MediaType.APPLICATION_XML)
                .get(ListaProcesos.class);
    }
}
