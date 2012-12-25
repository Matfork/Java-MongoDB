/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionMongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;

/**
 *
 * @author Matt
 */
public class Conexion {
    
    private Conexion() {
    }
    
    public static DB getConnection() throws Exception{
		  
   /* MongoClient mongoClient = new MongoClient();    
      MongoClient mongoClient = new MongoClient( "localhost" );*/
      
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        
    // or, to connect to a replica set, supply a seed list of members
    /*MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27017),
                                        new ServerAddress("localhost", 27018),
                                        new ServerAddress("localhost", 27019)));*/

        //DB db = mongoClient.getDB( "mydb" );  
        DB db = mongoClient.getDB( "mongoDBIntroduccion" );  
        return db;
    }
}


