import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * Class for the Admin Section
 */

public class Admin
{
    public static Scanner sc = new Scanner(System.in);

    public static void MenuManager()
    {
        Admin di = new Admin();
        int ch;

        do
        {

            System.out.println("==============================================================================");
            System.out.println("                                 OPTIONS                                      ");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("                          [1] Insert Data");
            System.out.println("                          [2] Display the Datas");
            System.out.println("                          [3] Update the Data");
            System.out.println("                          [4] Delete the Data");
            System.out.println("                          [5] Logout");
            System.out.println("==============================================================================");

            System.out.print("    Enter your choice: ");
            ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> di.insertData();
                case 2 -> di.displayData();
                case 3 -> di.updateData();
                case 4 -> di.deleteData();
                case 5 -> Login.logout();
                default -> {
                }
            }
        }
        while(ch!= 5);
    }


    // Insertion of Data into the Database Table
    private static void insertData()
    {
        Menu item = new Menu();
        System.out.println("==============================================================================");
        System.out.println("                               INSERT DATA                                    ");
        System.out.println("------------------------------------------------------------------------------");
        int itemCode = Console.getInt("    Enter the item code: ");
        item.setMenu_code(itemCode);

        System.out.print("    Enter the item name: ");
        String itemName = sc.nextLine();
        item.setMenu_item(itemName);

        System.out.print("    Enter the item status: ");
        String itemStatus = sc.nextLine();
        item.setMenu_status(itemStatus);

        double itemPrice = Console.getDouble("    Enter the item price: ");
        item.setMenu_price(itemPrice);

        try
        {
            Connection con = DBConnect.getConnect();

            String sql = "INSERT into champas.champas_menu(code, menu_item, status, food_price) VALUES(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, item.getMenu_code());
            pst.setString(2, item.getMenu_item());
            pst.setString(3, item.getMenu_status());
            pst.setDouble(4, item.getMenu_price());

            int x = pst.executeUpdate();
            pst.close();
            con.close();

            if(x==1)
            {
                System.out.println("Record Inserted Succesfully!");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //Deletion of Data from the Database Table
    private void deleteData()
    {
        try
        {
            Connection con = DBConnect.getConnect();
            Menu item = new Menu();

            System.out.println("==============================================================================");
            System.out.println("                               DELETE DATA                                    ");
            System.out.println("------------------------------------------------------------------------------");
            int code = Console.getInt("    Enter the record number to delete: ");
            item.setMenu_code(code);

            String sql = "DELETE FROM champas_menu WHERE code=? ";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, item.getMenu_code());
            int x = pst.executeUpdate();

            pst.close();
            con.close();
            if(x==1)
            {
                System.out.println("Record Deleted Succesfully!");
            }
            else
            {
                System.out.println("Record Not Found!");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //Update Data from the Database Table
    private static void updateData()
    {
        Menu item = new Menu();

        System.out.println("==============================================================================");
        System.out.println("                               UPDATE DATA                                    ");
        System.out.println("------------------------------------------------------------------------------");
        int itemCode = Console.getInt("    Enter the code: ");
        item.setMenu_code(itemCode);

        System.out.print("    Enter the item name: ");
        String itemName = sc.nextLine();
        item.setMenu_item(itemName);

        System.out.print("    Enter the item status: ");
        String itemStatus = sc.nextLine();
        item.setMenu_status(itemStatus);

        System.out.print("    Enter the item price: ");
        double itemPrice = sc.nextDouble();
        item.setMenu_price(itemPrice);

        try
        {
            Connection con = DBConnect.getConnect();
            String str = "UPDATE champas.champas_menu SET menu_item=?, status=?, food_price=? WHERE code=?";
            PreparedStatement pst = con.prepareStatement(str);

            pst.setString(1, item.getMenu_item());
            pst.setString(2, item.getMenu_status());
            pst.setDouble(3, item.getMenu_price());
            pst.setInt(4, item.getMenu_code());

            int x = pst.executeUpdate();

            pst.close();
            con.close();

            if(x==1)
            {
                System.out.println("Update Done!");
            }
            else
            {
                System.out.println("Record not Found!");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //Display the Datas from the Database Table
    public void displayData()
    {
        try
        {
            Connection con = DBConnect.getConnect();

            PreparedStatement pst = con.prepareStatement("SELECT * FROM champas.champas_menu");

            ResultSet rs = pst.executeQuery();

            System.out.println("==============================================================================");
            System.out.println("                                   MENU                                       ");
            System.out.println("------------------------------------------------------------------------------");
            System.out.printf(String.format(" %-9s%-40s %-17s %-8s\n","CODE", "ITEM", "STATUS", "PRICE"));
            System.out.println("------------------------------------------------------------------------------");
            while(rs.next())
            {
                System.out.printf(String.format(" %-9s%-40s %-17s %-8s\n"
                        , rs.getString(2)
                        , rs.getString(3)
                        , rs.getString(4)
                        ,rs.getString(5)));
            }
            System.out.println("==============================================================================\n\n");
            rs.close();
            pst.close();
            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
