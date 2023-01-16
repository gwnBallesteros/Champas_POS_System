import java.security.NoSuchAlgorithmException;

public class Main {

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
                Login.PasswordCashier();
                Invoice invoice = new Invoice();
                Cashier.getLineItems(invoice);
                Cashier.paymentInput(invoice);
            }
        }
    }
}