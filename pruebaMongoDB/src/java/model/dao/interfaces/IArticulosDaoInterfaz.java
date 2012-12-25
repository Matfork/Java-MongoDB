/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import java.util.List;

/**
 *
 * @author Matt
 */
public interface IArticulosDaoInterfaz<T> {

	T getPorId(Integer id) throws Exception;
	List<T> selectQry() throws Exception;
	String insertar(T o) throws Exception;
	String actualiza(T o) throws Exception;
	void eliminar(Integer id) throws Exception;
        
        
   /*       //Sera un arreglo de objetos ya que este contendra tanto las noticias y categorias     
    public List<Object[]> librosQry();
    public List<Escritores> escritoresQry();
    
    //Metodos de Insercion
    public String librosInsert(Libros libro);
    public String escritoresInsert(Escritores escritor);
    
    //Metodos de Eliminacion
    public String librosDelete(Integer idLibro);
    public String escritoresDelete(Integer idEscritor) ;
    
    //Metodos de Get de una Fila
    public Libros librosGet(Integer idLibro);
    public Escritores escritoresGet(Integer idEscritor);
    
    //Metodos de Eliminacion
    public String librosUpdate(Libros libro);
    public String escritoresUpdate(Escritores escritor);
    
    //Metodos Adicionales
    public List<Escritores> escritoresCombo();
    */
}
