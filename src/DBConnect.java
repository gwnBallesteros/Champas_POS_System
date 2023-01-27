import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Connector to the MySql Database
 */
public class DBConnect
{
    public static Connection getConnect()
    {
        Connection con = null;

        try
        {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = DriverManager.getConnection("url-", "user", "password");

            return con;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
