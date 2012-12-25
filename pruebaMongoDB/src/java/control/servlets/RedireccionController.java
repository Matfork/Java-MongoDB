/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Matt
 */
@WebServlet(name = "RedireccionController", urlPatterns = {"/IrMenuAuditoriaArticulo","/IrMenuArticulo","/IrLogOff"})
public class RedireccionController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String alias = request.getServletPath();
        if (alias.equals("/IrMenuAuditoriaArticulo")) {
             redireccion(request, response,"menuAuditoriaArticulo.jsp");
        }else if (alias.equals("/IrMenuArticulo")) {
            redireccion(request, response,"menuArticulo.jsp");
        }else if (alias.equals("/IrLogOff")) {
            request.getSession().invalidate(); //Destruimos la sesion vigente
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);           
        }
    }

    private void redireccion(HttpServletRequest request, HttpServletResponse response,String direccion) {

        try {

            //Para Comprobar si una sesion no existe lo podemos hacer con las siguiente condiciones
            //if(request.getSession().getAttribute("UsuarioLoggedIn") != null){      

            if (request.getSession().getAttribute("UsuarioLoggedIn") != null) {
                RequestDispatcher rd = request.getRequestDispatcher(direccion);
                rd.forward(request, response);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
                rd.forward(request, response);
            }

        } catch (ServletException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
 
}
