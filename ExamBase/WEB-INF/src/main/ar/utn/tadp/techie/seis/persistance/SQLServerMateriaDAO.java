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
import java.util.Collection;
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
public class SQLServerMateriaDAO
        implements MateriaDAO
{

    SQLServerConnectionPool pool;

    public SQLServerMateriaDAO()
    {
        String dbHost = ExamBaseProperties.getInstance().getProperty("db.host");
        String dbLoginUser = ExamBaseProperties.getInstance().getProperty(
                "db.loginUser");
        String dbLoginPass = ExamBaseProperties.getInstance().getProperty(
                "db.loginPass");
        int dbConnectionsCached = Integer.parseInt(ExamBaseProperties.getInstance().getProperty("db.connectionsCached"));

        pool = new SQLServerConnectionPool(dbConnectionsCached, dbHost,
                                           dbLoginUser, dbLoginPass);
    }

    public Set<String> listarMaterias()
    {
        Connection conn = pool.getConnection();
        // Por si hay otro thread usando esta conneccion me bloqueo esperando
        // que termine
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
                System.out.println("Error al hacer el query de nombres de materias " + e);
            }
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
                // instancio la materia
                Materia retval = new Materia(nombre);
                Map<Integer, ItemExamen> idsItems = new HashMap<Integer, ItemExamen>();
                // nombre = "\'"+nombre+"\'";

                // instancio los ejercicios
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
                    Ejercicio nuevoEjercicio = new Ejercicio(unidadTematica,
                                                             complejidad, enunciado, tipo);
                    nuevoEjercicio.setId(itemId);
                    retval.addEjercicio(nuevoEjercicio);
                    idsItems.put(itemId, nuevoEjercicio);
                }
                res.close();
                // instancio las preguntas
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
                    Pregunta nuevaPregunta = new ADesarrollar(unidadTematica,
                                                              complejidad, enunciado, tipo);
                    nuevaPregunta.setId(itemId);
                    nuevaPregunta.setCantidadDeVecesQueSeUso(vecesQueSeUso);
                    retval.addPregunta(nuevaPregunta);
                    idsItems.put(itemId, nuevaPregunta);
                }
                res.close();
                // examenes

                // primero las fechas
                Set<Examen> examenes = new HashSet<Examen>();
                query = "SELECT E.id_Examen, E.fecha " +
                        "FROM   Examen as E, Materia as M " +
                        "WHERE  M.id_materia = E.id_materia " +
                        "       AND M.nombre = ?";
                stat = conn.prepareStatement(query);
                stat.setString(1, nombre);
                res = stat.executeQuery();
                Map<Integer, String> relacionExamen = new HashMap<Integer, String>();

                while(res.next())
                {
                    relacionExamen.put(res.getInt("id_Examen"), res.getString("fecha"));
                }
                res.close();

                // despues los items
                // un query para cada examen
                for(int examenId : relacionExamen.keySet())
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
                        // Ya me guarde los items de esta materia, los saco de
                        // ahi
                        items.add(idsItems.get(itemId));
                    }
                    res.close();
                    String fechaString = relacionExamen.get(examenId);
                    try
                    {
                        Examen nuevoExamen = new Examen(
                                StringToCalendar(fechaString), items);
                        retval.addExamen(nuevoExamen);
                    }
                    catch(ExamenSinPreguntasNiEjerciciosException ex)
                    {
                        System.out.println("No se pudo cargar uno de los examenes: " + ex);
                    }

                }

                // Al fin se termino
                return retval;

            }
            catch(SQLException e)
            {
                System.out.println("Error al intentar cargar la materia " + nombre + "\n" + e);
            }
        }
        return null;
    }

    public static DateFormat getDateFormat()
    {
        // FIXME Hay un error en el formato cuando termina con cero los
        // milisegundos
        // Ejemplo guarde 17 salio 170 (o algo asi)
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    }

    /**
     * Recibe un Calendar y lo convierte a un String con el formato yyyy-MM-dd
     * kk:mm:ss.SSS para que lo interprete el SQL Server
     * 
     * @param cal
     *            el calendario
     * @return el string formateado
     */
    public static String CalendarToString(Calendar cal)
    {
        DateFormat format = getDateFormat();
        return format.format(cal.getTime());
    }

    /**
     * Recibe un String con una fecha en el formato yyyy-MM-dd kk:mm:ss.SSS ej:
     * 2008-04-22 00:00:00.000
     * 
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

    public Collection<String> getMateriasNameList()
    {
        Connection conn = pool.getConnection();
        synchronized(conn)
        {
            try
            {
                Collection<String> retval = new HashSet<String>();
                String query = "SELECT nombre FROM Materia";
                ResultSet res = conn.prepareStatement(query).executeQuery();
                while(res.next())
                {
                    retval.add(res.getString("nombre"));
                }
                return retval;
            }
            catch(SQLException e)
            {
                System.err.println("Error no se pudiaron listar las Materias");
                return new HashSet<String>();
            }
        }
    }

    public Collection<String> getUnidadesList(String materia)
    {
        Connection conn = pool.getConnection();
        Collection<String> retval = new HashSet<String>();
        synchronized(conn)
        {
            try
            {
                String query = "SELECT UT.nombre " +
                        "FROM UnidadTematica as UT, Materia as M " +
                        "WHERE   UT.id_materia = M.id_materia " +
                        "    AND M.nombre = ?";
                PreparedStatement stat = conn.prepareStatement(query);
                stat.setString(1, materia);
                ResultSet res = stat.executeQuery();

                while(res.next())
                {
                    retval.add(res.getString("nombre"));
                }
            }
            catch(SQLException e)
            {
                System.err.println("Error al leer las unidades tematicas de la materia " + materia);
            }
            return retval;

        }
    }

    public void addUnidadTematica(String materia, String unidad)
    {
        Connection conn = pool.getConnection();

        synchronized(conn)
        {
            try
            {
                String query = "INSERT	UnidadTematica (id_materia, nombre) " +
                        "SELECT	id_materia, " + "         ? " +
                        "FROM	Materia " +
                        "WHERE	Materia.nombre = ? ";
                PreparedStatement stat = conn.prepareStatement(query);
                stat.setString(1, unidad);
                stat.setString(2, materia);
                stat.execute();

            }
            catch(SQLException e)
            {
                System.err.println("Error al grabar la unidad tematica " + unidad + " de la materia " + materia);
            }

        }
    }

    public void addItem(String materia, ItemExamen item)
    {
        Connection conn = pool.getConnection();
        synchronized(conn)
        {
            try
            {
                String query = "INSERT	ItemExamen  (id_materia, complejidad, cantidadDeVecesQueSeUso, textoEnunciado, id_tipoTeoricaPractica, id_tipoPreguntaEjercicio,id_UnidadTematica) "  +
                        "SELECT	M.id_materia,?, " +// -- Complejidad
                        "0, " + //-- Cant Veces Usadas (cero apenas se carga)
                        "?, " + //-- Texto Enunciado
                        "TTP.id_TipoTeoricaPractica, " + 
                        " 'P', " +
                        "UT.id_UnidadTematica " +
                        "FROM	Materia M, UnidadTematica UT, TipoTeoricaPractica TTP " +
                        "WHERE	M.nombre = ? " + //-- Nombre de la materia
                        "AND	UT.nombre= ? " + //-- Nombre de la Unidad Tematica
                        "AND	M.id_materia = UT.id_materia " +
                        "AND	TTP.Descripcion = ? "; //-- Teorico, Practico, TeoricoPractico

                PreparedStatement stat = conn.prepareStatement(query);
                stat.setInt(1, item.getComplejidad());
                stat.setString(2, item.getTextoEnunciado());
                stat.setString(3, materia);
                stat.setString(4, item.getUnidadTematica());
                stat.setString(5, item.getTipo().toString());
                stat.execute();
            }catch(SQLException e) {
                
            }
        }

    }

    public void addExamen(String materia, Examen examen) throws SQLException
    {
        Connection conn = pool.getConnection();

        synchronized(conn)
        {
            try
            {
                String fechaString = CalendarToString(examen.getFecha());

                //la tabla examen
                String query = "INSERT	Examen (id_materia, fecha) " +
                        "SELECT	id_materia," +
                        "         ? " +
                        "FROM	Materia " +
                        "WHERE	nombre = ?";
                PreparedStatement stat = conn.prepareStatement(query);
                stat.setString(1, fechaString);
                stat.setString(2, materia);
                stat.execute();

                //las unidades
                for(String unidad : examen.getUnidades())
                {
                    query = "INSERT	Examen_UnidadesTematicas ( " +
                            "id_Examen, " +
                            "id_Materia, " +
                            "id_UnidadTematica " +
                            ") " +
                            "SELECT	EX.id_Examen, " +
                            "		UT.id_Materia, " +
                            "		UT.id_UnidadTematica " +
                            "FROM	Materia M, Examen EX, UnidadTematica UT " +
                            "WHERE	M.id_Materia = EX.id_Materia " +
                            "AND		EX.id_Materia = UT.id_Materia " +
                            "AND		M.nombre	= ? " +//nombre materia
                            "AND		EX.fecha	= ? " +//fecha
                            "AND		UT.nombre	= ? ";//unidad
                    stat = conn.prepareStatement(query);
                    stat.setString(1, materia);
                    stat.setString(2, fechaString);
                    stat.setString(3, unidad);
                    stat.execute();
                }
                //las preguntas
                for(ItemExamen item : examen.getItems())
                {
                    query = "INSERT	Examen_ItemExamen ( " +
                            "           id_Examen, " +
                            "           id_ItemExamen " +
                            "           ) " +
                            "SELECT	EX.id_Examen, " +
                            "           ? " +// -- Id ItemExamen 
                            "FROM	Materia M, Examen EX " +
                            "WHERE	M.id_Materia = EX.id_Materia " +
                            "AND	M.nombre	= ? " +//-- Materia "+
                            "AND	EX.fecha	= ?"; // -- Fecha";
                    stat = conn.prepareStatement(query);
                    stat.setInt(1, item.getId());
                    stat.setString(2, materia);
                    stat.setString(3, fechaString);
                    stat.execute();
                }

            }
            catch(SQLException e)
            {
                System.err.println("Error al grabar un examen nuevo en la materia " + materia + "\n" + e);
                throw e;
            }

        }
    }
}
