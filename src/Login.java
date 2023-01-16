import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Login
{
    public static void Login()
    {
        System.out.println("==============================================================================");
        System.out.println("                                    LOG-IN                                    ");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("                                  [1] ADMIN                                   ");
        System.out.println("                                  [2] CASHIER                                 ");
        System.out.println("------------------------------------------------------------------------------");
    }

    public static void loginAlert()
    {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("|                           LOGIN SUCCESSFULLY!                              |");
        System.out.println("------------------------------------------------------------------------------");
    }
    public static void logout()
    {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("|                          LOGOUT SUCCESSFULLY!                              |");
        System.out.println("------------------------------------------------------------------------------");
    }
    public static void footer()
    {
        System.out.println("==============================================================================");
        System.out.println("|                      CHAMPAS MEXICAN GRILL POS SYSTEM                      |");
        System.out.println("==============================================================================");
    }

    public static void splash()
    {
        System.out.println("==============================================================================");
        System.out.println("|                                                                            |");
        System.out.println("|                                  CHAMPAS                                   |");
        System.out.println("|                                 POS SYSTEM                                 |");
        System.out.println("|                                                                            |");
        System.out.println("==============================================================================");

    }

    public static String encryptString(String input) throws NoSuchAlgorithmException
    {
        //MessageDigest works with MD2, MD5, SHA-1, SHA-224, SHA-256
        //SHA-384, SHA-512

        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] messageDigest = md.digest(input.getBytes());

        BigInteger bigInt = new BigInteger(1, messageDigest);

        return bigInt.toString(16);
    }

    public static void PasswordAdmin() throws NoSuchAlgorithmException
    {
        Encryptor encryptor = new Encryptor();

        //String password = "ADMIN123";
        String hashpass = "3f7caa3d471688b704b73e9a77b1107f";

        Scanner scanner = new Scanner(System.in);
        String userInput ="";
        do {
            System.out.print("    Enter Password: ");
            userInput = scanner.nextLine();

        } while(!Encryptor.encryptString(userInput).equals(hashpass));
    }

    public static void PasswordCashier() throws NoSuchAlgorithmException
    {
        Encryptor encryptor = new Encryptor();

        //String password = "CASHIER123";
        String hashpass = "2346a4255d4d51750c5d52693356acf3";

        Scanner scanner = new Scanner(System.in);
        String userInput ="";
        do {
            System.out.print("    Enter Password: ");
            userInput = scanner.nextLine();

        } while(!Encryptor.encryptString(userInput).equals(hashpass));

    }

}
