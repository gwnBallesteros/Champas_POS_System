/****************************************************
 CONSOLE POS SYSTEM
 STORE: CHAMPAS MEXICAN GRILL
 VERSION 1.0.0: 01/17/2023
*****************************************************/

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);

    /**
     * Main Function
     */
    public static void main(String[] args) throws NoSuchAlgorithmException
    {
        Login.splash();
        String login;
        do
        {
            Login.Login();
            int acctType = Console.getInt("    Choose Account: ",1,2);

            switch (acctType)
            {
                case 1 -> admin();
                case 2 -> cashier();
            }
            System.out.print("    Log-in Again? (y/n) ");
            login = sc.next();
        } while(login.equalsIgnoreCase("Y"));
        Login.footer();
    }


    /**
     * Function for the Admin using the Admin Class
     */
    private static void admin() throws NoSuchAlgorithmException
    {
        Login.PasswordAdmin();
        Login.loginAlert();
        Admin.MenuManager();
    }


    /**
     * Function for the Cashier using the Cashier Class
     */
    private static void cashier() throws NoSuchAlgorithmException
    {
        String choice;
        String transChoice;
        Login.PasswordCashier();
        Login.loginAlert();
        do
        {
            Invoice invoice = new Invoice();
            do
            {
                Cashier.getLineItems(invoice);
                Cashier.totalOrders(invoice);
                System.out.print("    Add more? (y/n) ");
                choice = sc.next();
            } while(choice.equalsIgnoreCase("Y"));
            Cashier.paymentInput(invoice);

            System.out.print("    Another transaction? (y/n) ");
            transChoice = sc.next();
        } while(transChoice.equalsIgnoreCase("Y"));
        Login.logout();
    }
}
