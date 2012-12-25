/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionMongo;

import com.mongodb.*;
import com.mongodb.util.JSON;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jboss.weld.util.collections.ArraySet;

/**
 *
 * @author Matt
 */
public class prueba {
    public static void main(String[] args) {
        Set<String> lista = new ArraySet<String>();
        try {
            DB db = Conexion.getConnection();
            //Nombre de Colecciones
            lista = db.getCollectionNames();
            /*
            for (String s : lista) {
                System.out.println(s);
            
           }     
           */  
           
            //Solo una coleccion
             db.setWriteConcern(WriteConcern.JOURNALED);
             DBCollection coll = db.getCollection("things");
                
             
             //Insertando un documento en la coleccion
             /*
              {
                "name" : "MongoDB",
                "type" : "database",
                "count" : 1,
                "info" : {
                            x : 203,
                            y : 102
                            }
                }
              */
             
             BasicDBObject doc = new BasicDBObject("name", "MongoDB2").
                                       append("type", "database").
                                       append("count", 1)
                                      .append("info", new BasicDBObject("x", 203).append("y", 102));
             
             
             coll.insert(doc);
             
             DBObject myDoc = coll.findOne(); //sino encuentra nada, entonces retorna null
             System.out.println("Resultado:" + myDoc);
             
             
             //Obtenemos todo los documentos en un cursor
             System.out.println("Monstrando Cursor:");
              DBCursor cursor = coll.find();
                try {
                    while(cursor.hasNext()) {
                        System.out.println(cursor.next());
                    }
                } finally {
                    cursor.close();
                }
              
              //Obtenemos un solo documento mediante un crietio de busqueda
               System.out.println("Solo un registro condicionado"); 
                BasicDBObject query = new BasicDBObject("name", "MongoDB");
                cursor = coll.find(query);

                try {
                    while(cursor.hasNext()) {
                        System.out.println(cursor.next());
                    }
                } finally {
                    cursor.close();
                }
                
               //Obtenemos un solo documento mediante un crietio de busqueda mas condiciones
                System.out.println("Mostrando documento condicionado:");
                query = new BasicDBObject("x", new BasicDBObject("$gte", 3.0))
                                        .append("j", new BasicDBObject("$lt", 10.0));
                
                //db.things.find({x:{$gte:3},j:{$lt:10}})
                System.out.println(query.toString());
              
                cursor = coll.find(query);
              
                try {
                    while(cursor.hasNext()) {
                        System.out.println("Resultado: " + cursor.next());
                    }
                } finally {
                    cursor.close();
                }
                       
               //Obtenemos el ultimo documento insertadp
               //db.collection.find().sort( { _id : -1 } ).limit(1);
                System.out.println("Ultima Fila insertada:");
                
                query = new BasicDBObject("_id",-1);
                cursor = coll.find().sort(query);
                
                try {
                    while(cursor.hasNext()) {
                        System.out.println(cursor.next());
                    }
                } finally {
                    cursor.close();
                }                
                
                //Obtener con un find un subdocument
                System.out.println("Lo Esperado");
                
                
                  db = Conexion.getConnection();
                    coll = db.getCollection("usuarios");

                    query = new BasicDBObject("nick", "matfork").append("clave", "matfork");
                    System.out.println(query);
                    DBObject resultado = coll.findOne(query);
                    
                    System.out.println(resultado);
                    System.out.println(resultado.get("nombre"));
                    System.out.println(resultado.get("id"));
                    
                    DBObject tipoUsuario = (DBObject) resultado.get("tipoUsuario");
                    System.out.println(tipoUsuario.get("idTipo"));
                    System.out.println(tipoUsuario.get("tipo"));
                    
            
        } catch (Exception ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
