import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MenuDB
{
    public static Menu getMenu(int menu_code)
    {
        Menu m = new Menu();

        try
        {
            Connection con = DBConnect.getConnect();

            ResultSet rs;
            String str = "SELECT menu_item,food_price FROM champas.champas_menu WHERE code=" + menu_code;
            PreparedStatement pst = con.prepareStatement(str);

            rs = pst.executeQuery();

            while(rs.next())
            {
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
