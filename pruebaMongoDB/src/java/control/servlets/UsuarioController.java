package control.servlets;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import dto.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.clases.UsuarioDaoClase;

@WebServlet(name = "UsuarioController", urlPatterns = {"/UsuarioDaoVerificarLogin","/UsuarioIngresoArticulo","/GetUsuarioInfo","/RedireccionPagina"})
public class UsuarioController extends HttpServlet {

    //El metodo service es un metodo que viene por defecto con los servlets, lo que hace
    //es reemplazar a los metodos doPost y doGet por uno solo, asi que si se envia informacion
    //ya sea por POST o GET el metodo service lo capturara, es por eso que en este servlet
    //se ha eliminado los metodos doPost y doGet que redireccionaban al metodo processrequest
    @Override
    protected void service(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String alias = request.getServletPath();
        if (alias.equals("/UsuarioDaoVerificarLogin")) {
            verificarLogin(request, response);
        }else if (alias.equals("/UsuarioIngresoArticulo")) {
            redireccionArticulo(request, response);
        }else if(alias.equals("/GetUsuarioInfo")){
            getUsuarioInfo(request,response);
        }else if(alias.equals("/RedireccionPagina")){
            redireccionCuelquierPagina(request,response);
	}
    }

    private void verificarLogin(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //Clase Gson
            Gson g = new Gson();
            StringWriter salida = new StringWriter();
            JsonWriter writer = new JsonWriter(salida);

            //Printwriter sera la salida que se enviara de regreso por ajax
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            // Dato
            String nick = request.getParameter("vNick");
            String pass = request.getParameter("vPass");

            // Proceso                        
            UsuarioDaoClase daoUsuario = new UsuarioDaoClase();
            Usuario u = daoUsuario.getVerificarUsuario(nick, pass);


            if (u != null) { //Solo si la verificacion es correcta, se puede forwardear la informacion del usuario logeado a la pagina de menu
                request.getSession().setAttribute("UsuarioLoggedIn", u);
                request.getSession().setMaxInactiveInterval(2*60);
                // RequestDispatcher rd = request.getRequestDispatcher("mostrarArticulos.jsp");
                // rd.forward(request, response);

                //Jseando con Gson
                writer.beginObject();
                writer.name("loginSatisfactorio").value("1");
                writer.name("mensaje").value("Bienvenido Usuario " + u.getNomUsuario()); // "edad" : 21
                writer.endObject();
                writer.close();
                
                out.println(salida);
                out.close();

            } //De otra forma se procedera a mostrar un mensaje de error en el index
            else {
                //   request.setAttribute("error", "El Usuario o Contraseña Ingresados son Incorrectos");   
                //Jseando con Gson
                writer.beginObject();
                writer.name("loginSatisfactorio").value("0");
                writer.name("mensaje").value("El Usuario o Contraseña Ingresados son Incorrectos");
                writer.endObject();
                writer.close();

                out.println(salida);
                out.close();

            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            System.out.println(e.getMessage());
        }

    }

    private void redireccionArticulo(HttpServletRequest request, HttpServletResponse response) {
            
        try {
            
            //Para Comprobar si una sesion no existe lo podemos hacer con las siguiente condiciones
                //if(request.getSession().getAttribute("UsuarioLoggedIn") != null){      
            
            if(request.getSession().getAttribute("UsuarioLoggedIn") != null){            
                RequestDispatcher rd = request.getRequestDispatcher("menuArticulo.jsp");
                rd.forward(request, response);
            }else{
                 RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
                rd.forward(request, response);
            }
            
        } catch (ServletException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private void getUsuarioInfo(HttpServletRequest request, 
					HttpServletResponse response) 
					throws ServletException, IOException {
		try {
                        response.setContentType("text/html;charset=UTF-8");
                        PrintWriter out = response.getWriter();
                        
                        Usuario u = (Usuario) request.getSession().getAttribute("UsuarioLoggedIn");
                    
			out.println(u.getNomUsuario());
                        out.close();
                        
		} catch (Exception e) {
                           
			request.setAttribute("error", e.getMessage());
		}				
	}

     
     //redireccion para cualquier pagina, comprobando si la session existe, previamente se debe de haber creado el javascript que permita ingresar a esta funcion en las vistas
    private void redireccionCuelquierPagina(HttpServletRequest request, HttpServletResponse response) {
        try {
            String enlace = request.getParameter("enlace");
            
            //Para Comprobar si una sesion no existe lo podemos hacer con las siguiente condiciones
                //if(request.getSession().getAttribute("UsuarioLoggedIn") != null){                  
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            if(request.getSession().getAttribute("UsuarioLoggedIn") != null){            
                out.println("1");
            }else{
                out.println("0");               
            }            
            out.close();
        
        } catch (IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}