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

        switch (acctType) {
            case 1 -> {
                Login.PasswordAdmin();
                Admin.MenuManager();
            }
            case 2 -> {
                Login.PasswordCashier();
                Invoice invoice = new Invoice();
                Cashier.getLineItems(invoice);
                Cashier.paymentInput(invoice);
            }
        }
    }
}