import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect
{
    public static Connection getConnect()
    {
        Connection con = null;

        try
        {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/champas", "root", "THELASTSAMURAIch5@");

            return con;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
