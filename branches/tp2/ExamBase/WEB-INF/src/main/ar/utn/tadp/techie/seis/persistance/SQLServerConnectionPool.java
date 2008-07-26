/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.utn.tadp.techie.seis.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author xuan
 */
public class SQLServerConnectionPool
{

    private int connections;
    private Map<Long,Connection> connectionsMap;
    
    
    private static boolean DriverRegistered = false;
    
    public SQLServerConnectionPool(int size, String url, String user,String pass)
    {
        this.connections = size;
        
        registrateDriver();
        
        String connectionUrl =  "jdbc:sqlserver://" +
                                             url + ";" +
                                            "databaseName=Exam_Base;" +
                                            "user=" + user + ";" +
                                            "password=" + pass +";";

        connectionsMap = new HashMap<Long,Connection>();
        
        for(int i=0;i<size;i++)
        {
            try
            {
                Connection newConnection = DriverManager.getConnection(connectionUrl);
                connectionsMap.put(System.currentTimeMillis(), newConnection);
            }
            catch(SQLException e)
            {
                System.out.println("No se pudo conectar al server " + e);
            }
        }
    }
    /**
     * Devuelve una conexion del pool, la que se uso antes
     * @return
     */
    synchronized public Connection getConnection()
    {
        Long older = Collections.min(connectionsMap.keySet());
        Connection retval = connectionsMap.get(older);
        connectionsMap.remove(older);
        connectionsMap.put(System.currentTimeMillis(), retval);
        return retval;
    }
    
    
    
    /**
     *  Registra el driver JDBC de SQL Server
     */
    public static void registrateDriver()
    {
        if(DriverRegistered)
            return;
                
        try
	{
	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            DriverRegistered = true;
	}
	catch(ClassNotFoundException e)
	{
	    System.out.println("No se pudo registrar el componente jdbc"+": " +e);
	}
        
    }
}
