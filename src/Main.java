import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws NoSuchAlgorithmException
    {
        //LOGIN
        Login.splash();
        String login;
        do
        {
            //String logIn;
            Login.Login();
            int acctType = Console.getInt("    Choose Account: ",1,2);

            switch (acctType) {
                case 1 -> {
                    Login.PasswordAdmin();
                    Login.loginAlert();
                    Admin.MenuManager();
                }
                case 2 -> {
                    String choice;
                    String transChoice;
                    Login.PasswordCashier();
                    Login.loginAlert();
                    do{
                        Invoice invoice = new Invoice();
                        do
                        {
                            Cashier.getLineItems(invoice);
                            Cashier.totalOrders(invoice);
                            System.out.print("    Add more: (y/n) ");
                            choice = sc.next();
                        } while(choice.equalsIgnoreCase("Y"));
                        Cashier.paymentInput(invoice);

                        System.out.print("    Another transaction: (y/n) ");
                        transChoice = sc.next();
                    } while(transChoice.equalsIgnoreCase("Y"));
                    Login.logout();
                }
            }
            System.out.print("    Login Again: (y/n) ");
            login = sc.next();
        } while(login.equalsIgnoreCase("Y"));
        Login.footer();
    }
}