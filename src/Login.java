import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Login
{
    public static void Login()
    {
        splash();
        System.out.println("                        LOG-IN                        ");
        System.out.println("------------------------------------------------------");
        System.out.println("                      [1] ADMIN                       ");
        System.out.println("                      [2] CASHIER                     ");
        System.out.println("------------------------------------------------------");
    }

    public static void splash()
    {
        System.out.println("=====================================================");
        System.out.println("|                                                    |");
        System.out.println("|                      CHAMPAS                       |");
        System.out.println("|                     POS SYSTEM                     |");
        System.out.println("|                                                    |");
        System.out.println("======================================================");

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

    public static boolean Password() throws NoSuchAlgorithmException
    {
        Encryptor encryptor = new Encryptor();

        String password = "Monkey123";
        String hashpass = "fdf50f40b095188140033f5533d9ca58";

        //System.out.println(Encryptor.encryptString(password));
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Password: ");

        String userInput = scanner.nextLine();

//        if(Encryptor.encryptString(userInput).equals(hashpass))
//        {
//             return true;
//        }
        if (!Encryptor.encryptString(userInput).equals(hashpass))
        {
            System.out.println(">>Wrong Password! Try Again!<<");
            return false;
        }

        return true;
    }
}
