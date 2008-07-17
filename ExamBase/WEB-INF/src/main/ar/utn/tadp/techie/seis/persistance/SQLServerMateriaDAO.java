/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.utn.tadp.techie.seis.persistance;

import ar.utn.tadp.techie.seis.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author xuan
 */
public class SQLServerMateriaDAO implements MateriaDAO
{

    SQLServerConnectionPool pool;
    
    public SQLServerMateriaDAO()
    {
        String dbHost = Config.getInstance().getProperty("db.host");
        String dbLoginUser = Config.getInstance().getProperty("db.loginUser");
        String dbLoginPass = Config.getInstance().getProperty("db.loginPass");
        int dbConnectionsCached = Integer.parseInt(Config.getInstance().getProperty("db.connectionsCached"));
        
        pool = new SQLServerConnectionPool(dbConnectionsCached, dbHost, dbLoginUser, dbLoginPass);
    }
            
    
    public Set<String> listarMaterias()
    {
        Connection conn = pool.getConnection();
        //Por si hay otro thread usando esta conneccion me bloqueo esperando que termine
        synchronized(conn)
        {
            ResultSet results = null;
            try
            {
                String query = "SELECT materia_id FROM Materia";
                Statement stat = conn.createStatement();
                results = stat.executeQuery(query);
                
                Set<String> retval = new HashSet<String>();
                while(results.next())
                {
                    retval.add(results.getString("materia_id"));
                }
                return retval;
                
            }
            catch(SQLException e)
            {
                System.out.println("Error al hacer el query de nombres de materias "+e);
            }
            //TODO Ver como manejar esto
//            finally
//            {
//                if(results != null)
//                    results.close();
//               
//            }
            return null;
        }
    }
    
    
    public Materia getMateria(String nombre)
    {
        Connection conn = pool.getConnection();
        //Por si hay otro thread usando esta conneccion me bloqueo esperando que termine
        synchronized(conn)
        {
            ResultSet results = null;
            try
            {
                String query = "SELECT ...";
                PreparedStatement stat = conn.prepareStatement(query);
                stat.setString(1, nombre);
                results = stat.executeQuery();
                
                //TODO
                
            }
            catch(SQLException e)
            {
                System.out.println("Error cargando la materia "+nombre+": " +e);
            }
            //TODO Ver como manejar esto
//            finally
//            {
//                if(results != null)
//                    results.close();
//               
//            }
            return null;
        }
    }

}
