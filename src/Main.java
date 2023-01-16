import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws NoSuchAlgorithmException
    {
        //LOGIN
        Login.Login();
        int acctType = Console.getInt("    Choose Account: ",1,2);

        switch (acctType) {
            case 1 -> {
                Login.PasswordAdmin();
                Admin.MenuManager();
            }
            case 2 -> {
                String choice = "";
                Login.PasswordCashier();
                Invoice invoice = new Invoice();
                do
                {
                    Cashier.getLineItems(invoice);
                    Cashier.totalOrders(invoice);
                    System.out.print("    Add more: (y/n) ");
                    choice = sc.next();
                } while(choice.equalsIgnoreCase("Y"));
                Cashier.paymentInput(invoice);
            }
        }
    }
}