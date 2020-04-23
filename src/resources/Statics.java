package resources;

import java.sql.Connection;

public class Statics {

    //Singleton.
    private static Connection connection;

    public static void setConnections(Connection connection)
    {
        Statics.connection=connection;
    }
    public static Connection getConnections()
    {
        return Statics.connection;
    }


}
