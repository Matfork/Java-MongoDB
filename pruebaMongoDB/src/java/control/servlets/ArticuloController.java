package control.servlets;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import dto.Articulo;
import dto.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.clases.ArticuloDaoClase;
import model.dao.interfaces.IArticulosDaoInterfaz;


@WebServlet(name = "ArticuloController", urlPatterns = {"/ArticuloDaoSelect","/ArticuloDaoInsert","/ArticuloDaoUpdate","/ArticuloDaoDelete","/EnviarAInsert","/EnviarAUpdate"})
public class ArticuloController extends HttpServlet {
	
    //El metodo service es un metodo que viene por defecto con los servlets, lo que hace
    //es reemplazar a los metodos doPost y doGet por uno solo, asi que si se envia informacion
    //ya sea por POST o GET el metodo service lo capturara, es por eso que en este servlet
    //se ha eliminado los metodos doPost y doGet que redireccionaban al metodo processrequest
        
        @Override
        protected void service(HttpServletRequest request, 
	HttpServletResponse response) 
					throws ServletException, IOException {
		
		String alias = request.getServletPath();
		if(alias.equals("/ArticuloDaoSelect")){
			selectAll(request,response);
		}else if(alias.equals("/EnviarAInsert")){
			enviarPagInsercion(request,response);
		}else if(alias.equals("/EnviarAUpdate")){
			enviarPagUpdate(request,response);
                }else if(alias.equals("/ArticuloDaoDelete")){
			eliminarFila(request,response);
                }else if(alias.equals("/ArticuloDaoInsert")){
			insertarFila(request,response);
		}else if(alias.equals("/ArticuloDaoUpdate")){
			actualizarFila(request,response);
		}
	}

	private void selectAll(HttpServletRequest request, 
					HttpServletResponse response) 
					throws ServletException, IOException {
		try {
			// Proceso
			IArticulosDaoInterfaz<Articulo> daoArticulo = new ArticuloDaoClase();
			//List<Articulo> lista = daoArticulo.getPorId(codArticulo);
                        List<Articulo> lista = daoArticulo.selectQry();
                        
			request.setAttribute("listaArticulos", lista);
                        
		} catch (Exception e) {
                           
			request.setAttribute("error", e.getMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher("mostrarArticulos.jsp");
		rd.forward(request, response);				
	}       
        
        private void enviarPagUpdate(HttpServletRequest request,
					HttpServletResponse response)
					throws ServletException, IOException {
		Articulo a;		
		try {
			Integer codigo = Integer.parseInt(request.getParameter("codigo"));
			ArticuloDaoClase dao = new ArticuloDaoClase();
			a = dao.getPorId(codigo);
			if (a == null) {
				a = new Articulo();				
			}
		} catch (Exception e) {
			a = new Articulo();			
		}
		
		request.setAttribute("articulo", a);
		RequestDispatcher rd = request.getRequestDispatcher("updateArticulo.jsp");
		rd.forward(request, response);
	}

	private void enviarPagInsercion(HttpServletRequest request,
					HttpServletResponse response)
					throws ServletException, IOException {		
		request.setAttribute("articulo", new Articulo());
		RequestDispatcher rd = request.getRequestDispatcher("insertarArticulo.jsp");
		rd.forward(request, response);
	}

    private void eliminarFila(HttpServletRequest request, HttpServletResponse response) {
              
                Integer codigo = Integer.parseInt(request.getParameter("codigoEliminar"));
                ArticuloDaoClase dao = new ArticuloDaoClase();
            try {
                dao.eliminar(codigo);

                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("La fila se Elimino correctamente!");
                out.close();
                
            } catch (Exception ex) {
                ex.getMessage();
            }
    }

    private void insertarFila(HttpServletRequest request, HttpServletResponse response) {
               
                //Json para controlar la insercion si fue correcta o no.
                Gson g = new Gson();
                StringWriter salida = new StringWriter();
                JsonWriter writer = new JsonWriter(salida);
            
                response.setContentType("text/html;charset=UTF-8");                 
                ArticuloDaoClase dao = new ArticuloDaoClase();
                String rpta="";
                
                Articulo ar = new Articulo();
                
                ar.setNomArticulo(request.getParameter("txtNomArticulo"));
                ar.setDescripcion(request.getParameter("txtADescripcion"));
                ar.setPrecio(Double.parseDouble(request.getParameter("txtPreCosto")));
                ar.setStock(Integer.parseInt(request.getParameter("txtStock")));

             //   JOptionPane.showMessageDialog(null,"entro!");
            try {
                PrintWriter out = response.getWriter();        
                
                rpta= dao.insertar(ar);                
              //  JOptionPane.showMessageDialog(null,rpta);
                
                if(rpta.equals("")){
                    writer.beginObject();
                    writer.name("insercionSatisfactoria").value("1");
                    writer.name("mensaje").value("La fila fue insertada Correctamente"); // "edad" : 21
                    writer.endObject();
                    writer.close();
                    
                    out.println(salida);
                    out.close();
                }else{                    
                    
                    writer.beginObject();
                    writer.name("insercionSatisfactoria").value("0");
                    writer.name("mensaje").value(rpta); 
                    writer.endObject();
                    writer.close();
                    
                    out.println(salida);
                    out.close();
                
                }
                
                out.println(rpta);
                out.close();
                
            } catch (Exception ex) {                                
               ex.getMessage();
            } 
    }

    private void actualizarFila(HttpServletRequest request, HttpServletResponse response) {
             //Json para controlar la Actualizacion si fue correcta o no.
                Gson g = new Gson();
                StringWriter salida = new StringWriter();
                JsonWriter writer = new JsonWriter(salida);
            
                response.setContentType("text/html;charset=UTF-8");                 
                ArticuloDaoClase dao = new ArticuloDaoClase();
                String rpta;
                
                Articulo ar = new Articulo();
                ar.setIdArticulo(Integer.parseInt(request.getParameter("txtCodArticulo")));
                ar.setNomArticulo(request.getParameter("txtNomArticulo"));
                ar.setDescripcion(request.getParameter("txtADescripcion"));
                ar.setPrecio(Double.parseDouble(request.getParameter("txtPreCosto")));                
                ar.setStock(Integer.parseInt(request.getParameter("txtStock")));                
                
             //   JOptionPane.showMessageDialog(null,"entro!");
            try {
                PrintWriter out = response.getWriter();        
                
                rpta= dao.actualiza(ar);                
              //  JOptionPane.showMessageDialog(null,rpta);
                
                if(rpta.equals("")){
                    writer.beginObject();
                    writer.name("insercionSatisfactoria").value("1");
                    writer.name("mensaje").value("La fila fue actualizada Correctamente"); // "edad" : 21
                    writer.endObject();
                    writer.close();
                    
                    out.println(salida);
                    out.close();
                }else{                    
                    
                    writer.beginObject();
                    writer.name("insercionSatisfactoria").value("0");
                    writer.name("mensaje").value(rpta); 
                    writer.endObject();
                    writer.close();
                    
                    out.println(salida);
                    out.close();
                
                }
                
                out.println(rpta);
                out.close();
                
            } catch (Exception ex) {                                
               ex.getMessage();
            } 
    }
        
	

}