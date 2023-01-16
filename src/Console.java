import java.util.Scanner;

public class Console
{
    private static Scanner sc = new Scanner(System.in);

    public static String getString(String prompt)
    {
        System.out.print(prompt);
        String s = sc.nextLine();
        return s;
    }
    public static int getInt(String prompt)
    {
        int i = 0;
        boolean isValid = false;
        while (!isValid)
        {
            System.out.print(prompt);
            if (sc.hasNextInt())
            {
                i = sc.nextInt();
                isValid = true;
            }
            else
            {
                System.out.println("                       ERROR! Invalid Input. Try again.\n");
            }

            sc.nextLine();
        }
        return i;
    }

    public static int getInt(String prompt, int min, int max)
    {
        int i = 0;
        boolean isValid = false;
        while(!isValid)
        {
            i = getInt(prompt);
            if(i < min)
            {
                System.out.println("                       ERROR! Invalid Input. Try again.\n");
            }
            else if (i > max)
            {
                System.out.println("                       ERROR! Invalid Input. Try again.\n");
            }
            else
                isValid = true;
        }
        return i;
    }

    public static double getDouble(String prompt)
    {
        double d = 0;
        boolean isValid = false;
        while (!isValid)
        {
            System.out.print(prompt);
            if (sc.hasNextDouble())
            {
                d = sc.nextDouble();
                isValid = true;
            }
            else
            {
                System.out.println("                       ERROR! Invalid Input. Try again.\n");
            }

            sc.nextLine();
        }
        return d;
    }
}
