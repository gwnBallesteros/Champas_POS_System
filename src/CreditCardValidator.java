/**
 * Luhn's Algorithm
 * Algorithm use to validate for card numbers including bank
 */

public class CreditCardValidator
{
    public static boolean validateCreditCardNumber(String input)
    {
        //Convert input to int
        int[] creditCardInt = new int[input.length()];

        for(int i = 0; i < input.length(); i++)
        {
            creditCardInt[i] = Integer.parseInt(input.substring(i, i + 1));
        }

        //Starting from the right, double each other, if greater than 9 mod 10 and + 1 to the remainder
        for(int i = creditCardInt.length - 2; i >= 0; i = i - 2 )
        {
            int tempValue = creditCardInt[i];
            tempValue = tempValue * 2;
            if(tempValue > 9)
            {
                tempValue = tempValue % 10 + 1;
            }
            creditCardInt[i] = tempValue;
        }

        //Add up all the digits
        int total = 0;
        for(int i = 0; i < creditCardInt.length; i++)
        {
            total += creditCardInt[i];
        }

        //If number is a multiple of 10, it is valid
        if(total % 10 == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
