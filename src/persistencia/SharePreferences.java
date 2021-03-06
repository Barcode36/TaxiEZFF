package persistencia;

import java.io.FileNotFoundException;
import java.util.logging.Logger;
import java.util.logging.Level;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

public class SharePreferences 
{

    public static final String FILE_NAME_CREDENCIALES ="config.json";// este archivo se guarda en la raiz de la carperta
    private boolean recordar;
    private String usuario;

    public SharePreferences()
    {
        
    }
    public SharePreferences(boolean recordar, String usuario) {
        this.recordar = recordar;
        this.usuario = usuario;
    }
    public void setRecordar(boolean recordar)
    {
        this.recordar=recordar;
    }
    public boolean getRecordar()
    {
        return recordar;
    }
    public void setUsuario(String usuario)
    {
        this.usuario=usuario;
    }
    public String getUsuario()
    {
        return usuario;
    }
    public static void initConfig()
    {
        Writer writer =null;
        SharePreferences sharePreferences = new SharePreferences(false, "");
        Gson gson = new Gson();
        try
        {
            writer  = new FileWriter(FILE_NAME_CREDENCIALES);
            gson.toJson(sharePreferences,writer);
        } 
        catch (IOException ex)
        {
            Logger.getLogger(SharePreferences.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try
            {
                writer.close();
            }
            catch (IOException ex)
            {
                Logger.getLogger(SharePreferences.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
  
    public static SharePreferences getCredenciales()
    {
        Gson gson = new Gson();
        SharePreferences sharedPreferences=null;
        try
        {
            sharedPreferences= gson.fromJson(new FileReader(FILE_NAME_CREDENCIALES),SharePreferences.class);
        } 
        catch (FileNotFoundException ex) 
        {
            initConfig();// genera el archivo por defecto .json donde se almacenar??n las credenciales para el inicio de decisi??n
            Logger.getLogger(SharePreferences.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sharedPreferences;
    }
    
    public static void setCredenciales(SharePreferences sharePreferences)
    {
        Gson gson = new Gson();
        Writer writer=null;
        try
        {
            writer = new FileWriter(FILE_NAME_CREDENCIALES);
            gson.toJson(sharePreferences, writer);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(SharePreferences.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try
            {
                writer.close();
            }
            catch (IOException ex)
            {
                Logger.getLogger(SharePreferences.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 

}

