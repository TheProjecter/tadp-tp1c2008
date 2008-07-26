/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.utn.tadp.techie.seis.persistance;

import ar.utn.tadp.techie.seis.ADesarrollar;
import ar.utn.tadp.techie.seis.Ejercicio;
import ar.utn.tadp.techie.seis.Examen;
import ar.utn.tadp.techie.seis.ExamenSinPreguntasNiEjerciciosException;
import ar.utn.tadp.techie.seis.ItemExamen;
import ar.utn.tadp.techie.seis.ItemExamen.TiposItem;
import ar.utn.tadp.techie.seis.Materia;
import ar.utn.tadp.techie.seis.Pregunta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
        String dbHost = ExamBaseProperties.getInstance().getProperty("db.host");
        String dbLoginUser = ExamBaseProperties.getInstance().getProperty("db.loginUser");
        String dbLoginPass = ExamBaseProperties.getInstance().getProperty("db.loginPass");
        int dbConnectionsCached = Integer.parseInt(ExamBaseProperties.getInstance().getProperty("db.connectionsCached"));
        
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
    

    public Materia getMateriaByNombre(String nombre)
    {
        Connection conn = pool.getConnection();
        synchronized(conn)
        {
            try
            {

                PreparedStatement stat;
                ResultSet res;
                String query;
                //instancio la materia
                Materia retval = new Materia(nombre);
                Map<Integer,ItemExamen> idsItems = new HashMap<Integer,ItemExamen>();
//                nombre = "\'"+nombre+"\'";

                //instancio los ejercicios
                Set<Ejercicio> ejercicios = new HashSet<Ejercicio>();
                query = "SELECT I.id_ItemExamen," +
                        "       I.complejidad," +
                        "       (select u.nombre from unidadtematica as u where u.id_unidadtematica = I.id_unidadtematica) as NombreUnidadTematica," +
                        "       I.cantidadDeVecesQueSeUso," +
                        "       I.textoEnunciado," +
                        "       TTP.Descripcion " +
                        "FROM ItemExamen I, Materia M, TipoTeoricaPractica TTP " +
                        "WHERE      I.id_TipoTeoricaPractica = TTP.id_TipoTeoricaPractica" +
                        "      AND  I.id_Materia = M.id_Materia" +
                        "      AND  I.id_tipoPreguntaEjercicio = 'E'" +
                        "      AND  M.nombre = ?";

                stat = conn.prepareStatement(query);
                stat.setString(1, nombre);
                res = stat.executeQuery();

                while(res.next())
                {
                    int itemId = res.getInt("id_ItemExamen");
                    String unidadTematica = res.getString("NombreUnidadTematica");
                    int complejidad = res.getInt("complejidad");
                    String enunciado = res.getString("textoEnunciado");
                    TiposItem tipo = TiposItem.valueOf(res.getString("Descripcion"));
                    Ejercicio nuevoEjercicio = new Ejercicio(unidadTematica, complejidad, enunciado, tipo);
                    retval.addEjercicio(nuevoEjercicio);
                    idsItems.put(itemId, nuevoEjercicio);
                }
                res.close();
                //instancio las preguntas
                Set<Pregunta> preguntas = new HashSet<Pregunta>();
                query = "SELECT I.id_ItemExamen," +
                        "       I.complejidad,     " +
                        "       (select u.nombre from unidadtematica as u where u.id_unidadtematica = I.id_unidadtematica) as NombreUnidadTematica," +
                        "       I.cantidadDeVecesQueSeUso,    " +
                        "       I.textoEnunciado,    " +
                        "       TTP.Descripcion " +
                        "FROM ItemExamen I, Materia M, TipoTeoricaPractica TTP " +
                        "WHERE       I.id_TipoTeoricaPractica = TTP.id_TipoTeoricaPractica " +
                        "       AND  I.id_Materia = M.id_Materia" +
                        "       AND  I.id_tipoPreguntaEjercicio = 'P'" +
                        "       AND  M.nombre = ?";

                stat = conn.prepareStatement(query);
                stat.setString(1, nombre);
                res = stat.executeQuery();

                while(res.next())
                {
                    int itemId = res.getInt("id_ItemExamen");
                    String unidadTematica = res.getString("NombreUnidadTematica");
                    int complejidad = res.getInt("complejidad");
                    int vecesQueSeUso = res.getInt("cantidadDeVecesQueSeUso");
                    String enunciado = res.getString("textoEnunciado");
                    TiposItem tipo = TiposItem.valueOf(res.getString("Descripcion"));
                    Pregunta nuevaPregunta = new ADesarrollar(unidadTematica, complejidad, enunciado, tipo);
                    retval.addPregunta(nuevaPregunta);
                    idsItems.put(itemId, nuevaPregunta);
                }
                res.close();
                //examenes

                //primero las fechas
                Set<Examen> examenes = new HashSet<Examen>();
                query = "SELECT E.id_Examen, E.fecha " +
                        "FROM   Examen as E, Materia as M " +
                        "WHERE  M.id_materia = E.id_materia " +
                        "       AND M.nombre = ?";
                stat = conn.prepareStatement(query);
                stat.setString(1, nombre);
                res = stat.executeQuery();
                Map<Integer,String> relacionExamen = new HashMap<Integer,String>();

                while(res.next())
                {
                    relacionExamen.put(res.getInt("id_Examen"), res.getString("fecha"));
                }
                res.close();

                //despues los items
                //un query para cada examen
                for(int examenId:relacionExamen.keySet())
                {
                    query = "SELECT id_ItemExamen " +
                            "FROM   Examen_ItemExamen " +
                            "WHERE  id_Examen = ?";
                    stat = conn.prepareStatement(query);
                    stat.setInt(1, examenId);
                    res = stat.executeQuery();
                    Set<ItemExamen> items = new HashSet<ItemExamen>();
                    while(res.next())
                    {
                        int itemId = res.getInt("id_ItemExamen");
                        //Ya me guarde los items de esta materia, los saco de ahi
                        items.add(idsItems.get(itemId));
                    }
                    res.close();
                    String fechaString = relacionExamen.get(examenId);
                    try
                    {
                        Examen nuevoExamen = new Examen(StringToCalendar(fechaString), items);
                        retval.addExamen(nuevoExamen);
                    }
                    catch(ExamenSinPreguntasNiEjerciciosException ex)
                    {
                        System.out.println("No se pudo cargar uno de los examenes: "+ex);
                    }
                    
                    
                }

                //Al fin se termino
                return retval;

            }
            catch(SQLException e)
            {
                System.out.println("Error al intentar cargar la materia " + nombre + "\n"+e);
            }
        }
        return null;
    }
    

    
    
    
    public static DateFormat getDateFormat()
    {
        //FIXME Hay un error en el formato cuando termina con cero los milisegundos
        //Ejemplo guarde 17 salio 170 (o algo asi)
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    }
    
    /**
     * Recibe un Calendar y lo convierte a un String con el formato  yyyy-MM-dd kk:mm:ss.SSS
     * para que lo interprete el SQL Server
     * @param cal el calendario
     * @return el string formateado
     */
    public static String CalendarToString(Calendar cal)
    {
        DateFormat format = getDateFormat();
        return format.format(cal.getTime());
    }
    /**
     * Recibe un String con una fecha en el formato yyyy-MM-dd kk:mm:ss.SSS
     * ej: 2008-04-22 00:00:00.000
     * @param str
     * @return
     */
    public static Calendar StringToCalendar(String str)
    {
        try
        {
            DateFormat format = getDateFormat();
            Date date = format.parse(str);

            Calendar cal = new GregorianCalendar();
            cal.setTime(date);
            return cal;
        }
        catch(ParseException ex)
        {
            return new GregorianCalendar();
        }
    }
    
}
