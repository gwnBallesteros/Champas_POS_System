import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Admin
{
    public static Scanner sc = new Scanner(System.in);

    public static void MenuManager()
    {
        Admin di = new Admin();
        int ch;

        do
        {
            System.out.println("\n======================================================");
            System.out.println("1. Insert the Record");
            System.out.println("2. Display the Records");
            System.out.println("3. Update the Records");
            System.out.println("4. Delete the Record");
            System.out.println("5. Exit\n");

            System.out.print("Enter your choice: ");
            ch = sc.nextInt();
            sc.nextLine();


            switch(ch)
            {
                case 1:
                    di.insertData();

                    break;

                case 2:
                    di.displayData();
                    break;

                case 3:
                    di.updateData();
                    break;

                case 4:
                    di.deleteData();
                    break;

                case 5:
                    System.out.println("Thank you for visiting us!");
                    break;

                default:
                    break;
            }
        }
        while(ch!= 5);
    }


    // Insertion of Data into the Database Table
    public static void insertData()
    {
        Menu item = new Menu();

        System.out.print("Enter the item name: ");
        String itemName = sc.nextLine();
        item.setMenu_item(itemName);

        System.out.print("Enter the item status: ");
        String itemStatus = sc.nextLine();
        item.setMenu_status(itemStatus);

        double itemPrice = Console.getDouble("Enter the item price: ");
        item.setMenu_price(itemPrice);

        try
        {
            Connection con = DBConnect.getConnect();

            String sql = "INSERT into champas.champas_menu(menu_item, status, food_price) VALUES(?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, item.getMenu_status());
            pst.setString(2, item.getMenu_item());
            pst.setDouble(3, item.getMenu_price());

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
    public void deleteData()
    {
        try
        {
            Connection con = DBConnect.getConnect();
            Menu item = new Menu();

            int id = Console.getInt("Enter the record number to delete: ");
            item.setMenu_id(id);

            String sql = "DELETE FROM champas_menu WHERE id=? ";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, item.getMenu_id());
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
    public static void updateData()
    {
        Menu item = new Menu();

        System.out.print("Enter the id: ");
        int itemId = sc.nextInt();
        item.setMenu_id(itemId);

        System.out.print("Enter the item name: ");
        String itemName = sc.nextLine();
        item.setMenu_item(itemName);

        System.out.print("Enter the item status: ");
        String itemStatus = sc.nextLine();
        item.setMenu_status(itemStatus);

        System.out.print("Enter the item price: ");
        double itemPrice = sc.nextDouble();
        item.setMenu_price(itemPrice);

        try
        {
            Connection con = DBConnect.getConnect();
            String str = "UPDATE champas.champas_menu SET menu_item=?, status=?, food_price=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(str);

            pst.setString(1, item.getMenu_status());
            pst.setString(2, item.getMenu_item());
            pst.setDouble(3, item.getMenu_price());
            pst.setInt(4, item.getMenu_id());

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

            System.out.println("======================================================");
            while(rs.next())
            {
                System.out.printf(String.format("%-12s %-25s %-40s %-55s\n"
                        ,rs.getString(1)
                        , rs.getString(2)
                        , rs.getString(3)
                        , rs.getString(4)));
            }
            System.out.println("======================================================\n");
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
