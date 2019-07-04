/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appweb;

import config.ConexionMod;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gianc
 */
public class SvtController extends HttpServlet {

    ConexionMod conexion = ConexionMod.getConexion();
    ProcesoJpaController entityDao = new ProcesoJpaController();

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.

    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acción
                = request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/") + 1);
        if (acción.equals("registrarproceso.do")) {
            cargarprocesos(request, response);
        }else if (acción.equals("procesos.do")) {
            mostrarprocesos(request, response);
        }else if (acción.equals("index.do")) {
            response.sendRedirect("index.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void cargarprocesos(HttpServletRequest request, HttpServletResponse response) {
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        List<String> tokens = new ArrayList<>();
        try {
            int cont = 0;
            int canti = 0;
            String str_proceso = null;
            String admin
                    = System.getenv("windir") + "\\system32\\" + "tasklist.exe /v";
            Process proceso = Runtime.getRuntime().exec(admin);
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream()));
            while ((str_proceso = input.readLine()) != null) {
                if (canti < cantidad) {
                    Proceso pro = new Proceso();
                    //System.out.println(str_proceso);
                    if (cont >= 4) {
                        String token = str_proceso.replace(" ", ".");
                        //System.out.println(token);
                        StringTokenizer tokenizer = new StringTokenizer(token, ".");
                        while (tokenizer.hasMoreTokens()) {
                            tokens.add(tokenizer.nextToken());
                            System.out.println(tokens.size());
                            if (tokens.size() == 10) {
                                pro = new Proceso(Integer.parseInt(tokens.get(1)), tokens.get(0), tokens.get(7), tokens.get(9), (tokens.get(9).length() < 10) ? 1 : 0);
                                /*pro.setNombre();
                                pro.setPid();
                                pro.setUsuario();
                                pro.setDescripcion();
                                pro.setPrioridad();*/
                                entityDao.create(pro);
                                tokens.clear();
                            }
                        }
                    }
                    cont++;
                }
                canti++;
            }
            input.close();
            cargar(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargar(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Proceso> procesos;
            procesos = entityDao.findProcesoEntities();
            request.getSession().setAttribute("procesos", procesos);
            response.sendRedirect("procesos.jsp");
        } catch (IOException ex) {
            Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarprocesos(HttpServletRequest request, HttpServletResponse response) {
        cargar(request, response);
    }

}
