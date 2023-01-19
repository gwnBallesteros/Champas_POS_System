import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * This is for Login essentials including the password and the Luhn's Algorithm
 */

public class Login
{

    /**
     * UI Console
     */
    public static void Login()
    {
        System.out.println("==============================================================================");
        System.out.println("                                    LOGIN                                    ");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("                                  [1] ADMIN                                   ");
        System.out.println("                                  [2] CASHIER                                 ");
        System.out.println("------------------------------------------------------------------------------");
    }

    public static void loginAlert()
    {
        System.out.println("\n\n------------------------------------------------------------------------------");
        System.out.println("|                           LOG-IN SUCCESSFULLY!                             |");
        System.out.println("------------------------------------------------------------------------------\n\n");
    }
    public static void logout()
    {
        System.out.println("\n\n------------------------------------------------------------------------------");
        System.out.println("|                          LOG-OUT SUCCESSFULLY!                             |");
        System.out.println("------------------------------------------------------------------------------\n\n");
    }
    public static void footer()
    {
        System.out.println("==============================================================================");
        System.out.println("|                      CHAMPAS MEXICAN GRILL POS SYSTEM                      |");
        System.out.println("==============================================================================");
    }

    public static void splash()
    {
        int program;
        Scanner sc = new Scanner(System.in);
        System.out.println("==============================================================================");
        System.out.println("|                                                                            |");
        System.out.println("|                            CHAMPAS MEXICAN GRILL                           |");
        System.out.println("|                                 POS SYSTEM                                 |");
        System.out.println("|                                                                            |");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("                              PRESS 1 TO CONTINUE");
        System.out.println("                              PRESS 2 TO EXIT");
        System.out.println("------------------------------------------------------------------------------");
        program = Console.getInt("                                  >> ",1,2);
        if(program == 2)
        {
            System.out.println("==============================================================================");
            System.exit(0);
        }

    }

    /**
     * Password section implementing the MD5 Hashing
     */
    public static void PasswordAdmin() throws NoSuchAlgorithmException
    {
        Encryptor encryptor = new Encryptor();

        String hashpass = "3f7caa3d471688b704b73e9a77b1107f";

        Scanner scanner = new Scanner(System.in);
        String userInput;
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
        String userInput;
        do {
            System.out.print("    Enter Password: ");
            userInput = scanner.nextLine();

        } while(!Encryptor.encryptString(userInput).equals(hashpass));

    }

}
