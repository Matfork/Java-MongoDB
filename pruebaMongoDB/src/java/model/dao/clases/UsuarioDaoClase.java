/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.clases;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import conexionMongo.Conexion;
import dto.Usuario;

/**
 *
 * @author Matt
 */
public class UsuarioDaoClase {

    public Usuario getVerificarUsuario(String nick, String pass) throws Exception {
        Usuario u = new Usuario();

        DB db = Conexion.getConnection();
        DBCollection coll = db.getCollection("usuarios");

        BasicDBObject query = new BasicDBObject("nick", nick).append("clave", pass);
        System.out.println(query);
        DBObject resultado = coll.findOne(query);
        
        if (resultado != null) {             
            
            Double cod = Double.parseDouble(resultado.get("id").toString());            
            DBObject tipoUsuario = (DBObject) resultado.get("tipoUsuario");//Como es un subdocument debemos de guardarlo en un DBObject para poder iterarlo
            Double idTipoUsuario = Double.parseDouble(tipoUsuario.get("idTipo").toString());
            
            u.setIdUsuario(cod.intValue() );
            u.setNomUsuario(resultado.get("nombre").toString());
            u.setNick(resultado.get("nick").toString());
            u.setPass(resultado.get("clave").toString());
            u.setIdTipoUsuario(idTipoUsuario.intValue());
            u.setTipoUsuario(tipoUsuario.get("tipo").toString());
        } else {
            u = null;
        }

        return u;
    }
}
