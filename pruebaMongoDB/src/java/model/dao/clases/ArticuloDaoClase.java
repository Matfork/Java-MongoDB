/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.clases;

import com.mongodb.*;
import conexionMongo.Conexion;
import dto.Articulo;
import java.util.ArrayList;
import java.util.List;
import model.dao.interfaces.IArticulosDaoInterfaz;

/**
 *
 * @author Matt
 */
public class ArticuloDaoClase implements IArticulosDaoInterfaz<Articulo> {

    @Override
    public Articulo getPorId(Integer id) throws Exception {        
         Articulo a = new Articulo();

        DB db = Conexion.getConnection();
        DBCollection coll = db.getCollection("articulos");

        BasicDBObject query = new BasicDBObject("id", id);
        DBObject resultado = coll.findOne(query);

        if (resultado != null) {
            Double idArticulo = Double.parseDouble(resultado.get("id").toString());
            Double stock = Double.parseDouble(resultado.get("stock").toString());
            
            a.setIdArticulo(idArticulo.intValue());
            a.setNomArticulo(resultado.get("nomArticulo").toString());
            a.setDescripcion(resultado.get("descripcion").toString());
            a.setPrecio(Double.parseDouble(resultado.get("precio").toString()));
            a.setStock(stock.intValue());
        }

        return a;
    }

    @Override
    public List<Articulo> selectQry() throws Exception {
        List<Articulo> lista = new ArrayList<Articulo>();
         Articulo a = null;

        DB db = Conexion.getConnection();
        DBCollection coll = db.getCollection("articulos");

        DBCursor cursor = coll.find();
        DBObject obj = null;
        try {
            while(cursor.hasNext()) {
            obj = cursor.next();    
            a = new Articulo();
            
            Double idArticulo = Double.parseDouble(obj.get("id").toString());
            Double stock = Double.parseDouble(obj.get("stock").toString());
            
            a.setIdArticulo(idArticulo.intValue());
            a.setNomArticulo(obj.get("nomArticulo").toString());
            a.setDescripcion(obj.get("descripcion").toString());
            a.setPrecio(Double.parseDouble(obj.get("precio").toString()));
            a.setStock(stock.intValue());
            lista.add(a);
            }
        } finally {
            cursor.close();
        }
     
        return lista;        
    }

    @Override
    public String insertar(Articulo ar) throws Exception {
        DB db = Conexion.getConnection();
        DBCollection coll = db.getCollection("articulos");
        
        //Obtenemos el ultimo id
        BasicDBObject query = new BasicDBObject("id", -1);
        DBCursor cur = coll.find().sort(query).limit(1);
        Double idArticulo;
        //Obtenemos el ultimo codigo ingresado y le sumamos 1, sino existe ultimo codigo le asignamos por defecto 1
        if(cur.hasNext())
             idArticulo = Double.parseDouble(cur.next().get("id").toString())+1;
        else
            idArticulo =1.0;
        cur.close();
        
        BasicDBObject nuevaData = new BasicDBObject();
        nuevaData.put("id", idArticulo.intValue());
        nuevaData.put("nomArticulo", ar.getNomArticulo());
        nuevaData.put("descripcion", ar.getDescripcion());
        nuevaData.put("precio", ar.getPrecio());
        nuevaData.put("stock", ar.getStock());
        
        coll.save(nuevaData );

         return "";
    }

    @Override
    public String actualiza(Articulo ar) throws Exception {
         
        DB db = Conexion.getConnection();
        DBCollection coll = db.getCollection("articulos");

        BasicDBObject filtro = new BasicDBObject("id", ar.getIdArticulo());
       
        BasicDBObject nuevaData = new BasicDBObject();
        nuevaData.put("id", ar.getIdArticulo());
        nuevaData.put("nomArticulo", ar.getNomArticulo());
        nuevaData.put("descripcion", ar.getDescripcion());
        nuevaData.put("precio", ar.getPrecio());
        nuevaData.put("stock", ar.getStock());
        
        coll.update(filtro,nuevaData );

       return  "";
    }

    @Override
    public void eliminar(Integer id) throws Exception {
        DB db = Conexion.getConnection();
        DBCollection coll = db.getCollection("articulos");

        BasicDBObject query = new BasicDBObject("id", id);
        coll.remove(query);
    }
    
   
}
