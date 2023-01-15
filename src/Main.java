import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws NoSuchAlgorithmException
    {
        //LOGIN
        Login.Login();
        int acctType = Console.getInt("Choose Account: ");
        System.out.println();

        switch(acctType)
        {
            case 1:
                Login.Password();
                if(Login.Password() == false)
                {
                    Login.Password();
                }
                Menu_manager.MenuManager();
                break;
            case 2:
                Login.Password();
                if(Login.Password() == false)
                {
                    Login.Password();
                }
                else if(Login.Password() == true)
                {
                    Invoice invoice = new Invoice();
                    Cashier.getLineItems(invoice);
                    Cashier.paymentInput(invoice);
                    //Cashier.displayInvoice(invoice);
                }
                break;
        }
    }
}