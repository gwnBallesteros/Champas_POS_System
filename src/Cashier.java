import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Cashier
{
    public static Scanner sc = new Scanner(System.in);
    private static double amount = 0.0;
    private static int quantity = 0;
    private static double totalAmount = 0;
    private static double change = 0.0;
    private static double cash = 0.0;

    public static void paymentInput(Invoice invoice)
    {
        //Cashier.getLineItems(invoice);
        System.out.println("======================================================");
        System.out.println("                     ORDERS                           ");
        System.out.println("------------------------------------------------------");
        System.out.println("Item \t\t\t\t Price\tQty\t\tTotal");
        System.out.println("------------------------------------------------------");
        for (LineItem lineItem : invoice.getLineItems())
        {
            Menu menu = lineItem.getMenu();

            System.out.printf(String.format("%-20s %-8s %-5s %-5s\n"
                    , menu.getMenu_item()
                    , menu.getPriceFormatted()
                    , lineItem.getQuantity()
                    , lineItem.getTotalFormatted()));
        }
        System.out.println("======================================================");
        System.out.println("\n\t\t\t\t\tInvoice Total: \t" + invoice.getTotalFormatted()+"\n");
        totalAmount = invoice.getTotal();
        quantity = (int) invoice.getTotalQuantity();
//        System.out.println("Amount:   " + amount );
        System.out.println("Quantity: " + quantity );

        System.out.println("------------------------------------------------------");
        System.out.println("                      PAYMENT                         ");
        System.out.println("------------------------------------------------------");
        System.out.print("Discount (if applicable): ");
        String discount = sc.nextLine();

        if(discount.equalsIgnoreCase("Y"))
        {
            System.out.println("------------------------------------------------------");
            System.out.println("                   Discount Type                      ");
            System.out.println("------------------------------------------------------");
            System.out.println("                   [1] PWD                            ");
            System.out.println("                   [2] Senior Citizen                 ");
            System.out.println("------------------------------------------------------");
            System.out.print("Enter discount type: ");
            int discountType = sc.nextInt();
            switch(discountType)
            {
                case 1:
                    totalAmount = invoice.getTotal() - (0.20 * invoice.getTotal());
                    break;
                case 2:
                    totalAmount = invoice.getTotal() - (0.25 * invoice.getTotal());
                    break;
            }

        }

        System.out.println("------------------------------------------------------");
        System.out.println("Total Amount: " + totalAmount);
        System.out.println("Mode of Payment (1: CASH) (2:CARD)");
        int mop = Console.getInt("Enter MOP: ");

        switch(mop) {
            case 1:
                System.out.println("------------------------------------------------------");
                do {
                    System.out.print("Enter cash: ");
                    cash = sc.nextDouble();
                    change = cash - totalAmount;
                } while(cash < totalAmount);
                System.out.println("------------------------------------------------------");
                System.out.println("Total Amount: " + totalAmount);
                change = (Math.round(change * 100.0)/100.0);
                System.out.println("Change: " + change);

                break;

            case 2:
                System.out.println("------------------------------------------------------");
                boolean cardValid;
                do {
                    System.out.print("Enter your card number: ");
                    String cardNumber = sc.next();

                    cardValid = CreditCardValidator.validateCreditCardNumber(cardNumber);
                } while (cardValid == false);

                System.out.println("Payment Successfully!");
                System.out.println("------------------------------------------------------");
                System.out.println("Total Amount: " + totalAmount);

                break;
        }

        System.out.print("Print the receipt: ");
        String print = sc.next();

        if(print.equalsIgnoreCase("Y"))
        {
            Cashier.displayInvoice(invoice);
        }
    }

    public static void getLineItems(Invoice invoice)
    {
        String choice = "y";
        while (choice.equalsIgnoreCase("y"))
        {
            //System.out.println("======================================================");
            String menuCode = Console.getString("Enter product code: ");
            int quantity = Console.getInt("Enter quantity: ");

            Menu menu = MenuDB.getMenu(menuCode);
            invoice.addItem(new LineItem(menu, quantity));

            choice = Console.getString("Another line item? (y/n): ");
            System.out.println();
        }
    }

    public static void displayInvoice(Invoice invoice)
    {
        System.out.println("======================================================");
        System.out.println("Item \t\t\t\t Price\tQty\t\tTotal");
        System.out.println("------------------------------------------------------");
        for (LineItem lineItem : invoice.getLineItems())
        {
            Menu menu = lineItem.getMenu();

            System.out.printf(String.format("%-20s %-8s %-5s %-5s\n"
                    , menu.getMenu_item()
                    , menu.getPriceFormatted()
                    , lineItem.getQuantity()
                    , lineItem.getTotalFormatted()));
        }
        System.out.println("------------------------------------------------------");
        System.out.println("\n\t\t\t\t\tInvoice Total: \tPhp " +totalAmount);
        System.out.println("\n\t\t\t\t\tCash tendered: \tPhp " +cash);
        System.out.println("\t\t\t\t\tChange:        \tPhp " +change);
    }
}
