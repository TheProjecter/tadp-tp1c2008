/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.utn.tadp.techie.seis.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import junit.framework.TestCase;

/**
 *
 * @author xuan
 */
public class MateriasDaoTestCase extends TestCase
{
    
    @Override
    public void setUp() throws Exception
    {
         SQLServerConnectionPool.registrateDriver();
    }
    
    public void testConnectionPool() throws Exception
    {
        String dbHost = ExamBaseProperties.getInstance().getProperty("db.host");
        String dbLoginUser = ExamBaseProperties.getInstance().getProperty("db.loginUser");
        String dbLoginPass = ExamBaseProperties.getInstance().getProperty("db.loginPass");
        int dbConnectionsCached = Integer.parseInt(ExamBaseProperties.getInstance().getProperty("db.connectionsCached"));
        
        SQLServerConnectionPool pool = new SQLServerConnectionPool(dbConnectionsCached, dbHost, dbLoginUser, dbLoginPass);
        
        Connection conn = pool.getConnection();
                
        synchronized(conn)
        {
            ResultSet results = null;
            try
            {
                String query = "SELECT * from Exam_Base..Materia where id_materia = ?";
                PreparedStatement stat = conn.prepareStatement(query);
                stat.setInt(1, 1);
                results = stat.executeQuery();
                String nombre = "";
                while(results.next())
                {
                   nombre = results.getString("nombre");
                    
                }
                
                //TODO
                
            }
            catch(SQLException e)
            {
               
            }
        
        }
        
    }
    
           
   
}
    
