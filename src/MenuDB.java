import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MenuDB
{
    public static Menu getMenu(String menu_code)
    {
        Menu m = new Menu();

        try
        {
            Connection con = DBConnect.getConnect();

            ResultSet rs;
            String str = "SELECT menu_item,food_price FROM champas.champas_menu WHERE id=" + menu_code;
            PreparedStatement pst = con.prepareStatement(str);

            rs = pst.executeQuery();

            while(rs.next())
            {
                //System.out.println(rs.getDouble("iprice"));
                String itemName = rs.getString("menu_item");
                m.setMenu_item(itemName);
                double foodPrice = rs.getDouble("food_price");
                m.setMenu_price(foodPrice);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return m;
    }


}
