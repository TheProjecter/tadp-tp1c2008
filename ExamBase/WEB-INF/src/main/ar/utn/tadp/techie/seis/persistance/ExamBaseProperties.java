/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.utn.tadp.techie.seis.persistance;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

/**
 * Propiedades del sistema.
 * Singleton que levanta la configuracion de config.ini y si el archivo
 * no existe intenta crearlo con las propiedades por defecto.
 * Las propiedades que no se cargaron se toman las por defecto
 * @author xuan
 */
public class ExamBaseProperties extends Properties
{

    private static ExamBaseProperties instance;
    
    public static ExamBaseProperties getInstance()
    {
        if(instance == null)
            instance = new ExamBaseProperties();
        return instance;
    }
  
    protected ExamBaseProperties()
    {
        super();
        //La propiedades por defecto
        Properties defaultProperties = new Properties();
        defaultProperties.setProperty("db.host", "localhost");
        defaultProperties.setProperty("db.loginUser", "sa");
        defaultProperties.setProperty("db.loginPass", "Feedback.512");
        defaultProperties.setProperty("db.connectionsCached", "3");
        
        
        try
        {
            defaultProperties.load(new FileReader("config.ini"));
        }
        catch(IOException ioe)
        {
            System.out.println("Archivo de propiedades no encontrado. Generando archivo con propiedades por defecto.");
            try
            {
                String comment = 
                        "################################################\n"+
                        "# Archivo de configuracion de sistema ExamBase  #\n"+
                        "#             Generado automagicamente          #\n"+
                        "#################################################";
                FileOutputStream out = new FileOutputStream("config.ini");
                defaultProperties.store(out, comment);
                out.close();
            }
            catch(IOException ex)
            {
                System.out.println("No se pudo generar un archivo de configuracion.");
            }
        }
        catch(IllegalArgumentException iarge)
        {
            System.out.println("Archivo de configuracion con errores de codificacion. No se pudo cargar, usando propiedades por defecto");
        } 
        defaults = defaultProperties;
    }
    /**
     * Guarda la configuracion actual en config.ini
     * @throws java.io.IOException
     */
    public void store() throws IOException
    {
        store(new FileWriter("config.ini"), "Ultima actualizacion " + new SimpleDateFormat("dd MMM yyyy").format(new Date()));
    }
    
    
}
