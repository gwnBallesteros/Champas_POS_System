import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Class for the Cashier Section
 */

public class Cashier
{
    public static Scanner sc = new Scanner(System.in);

    /**
     *Variables
     */
    private static int quantity = 0;
    private static double totalAmount = 0;
    private static double change = 0.0;
    private static double cash = 0.0;
    private static double tax = 0.0;
    private static  double subTotal = 0.0;
    private static String cardLabel;
    private static String cardHolder;


    /**
     * Function for the Total Orders
     */
    public static void totalOrders(Invoice invoice)
    {
        System.out.println("==============================================================================");
        System.out.println("                               TOTAL ORDERS                                   ");
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf(String.format("%-40s %-14s %-9s %-10S\n","ITEM", "PRICE", "QTY", "TOTAL"));
        System.out.println("------------------------------------------------------------------------------");
        for (LineItem lineItem : invoice.getLineItems())
        {
            Menu menu = lineItem.getMenu();

            System.out.printf(String.format(" %-40s%-14s %-9s %-10s\n"
                    , menu.getMenu_item()
                    , menu.getPriceFormatted()
                    , lineItem.getQuantity()
                    , lineItem.getTotalFormatted()));
        }
        System.out.println("==============================================================================");
        System.out.printf(String.format("%77s","Subtotal:  "+invoice.getTotalFormatted()+"\n"));
        totalAmount = invoice.getTotal();
        quantity = (int) invoice.getTotalQuantity();
        System.out.println("    Quantity: " + quantity );
        System.out.println("==============================================================================");
    }


    /**
     * Function for the Payment Section
     */
    public static void paymentInput(Invoice invoice)
    {
        int press;
        System.out.println("==============================================================================");
        System.out.println("                                  DISCOUNT                                    ");
        System.out.println("==============================================================================");
        press = Console.getInt("    Discount: (PRESS 1: YES / PRESS 2: NO) ");

        switch (press) {
            case 1 -> Cashier.discount(invoice);
        }

        System.out.println("==============================================================================");
        System.out.println("                                  AMOUNT                                      ");
        System.out.println("------------------------------------------------------------------------------");
        totalAmount = (Math.round(totalAmount*100.0)/100.0);
        System.out.println("    SubTotal: " + totalAmount);
        subTotal = totalAmount;
        tax = (Math.round((0.12 * totalAmount)*100.0)/100.0);
        System.out.println("    VAT (12%): " + tax);
        totalAmount = (Math.round((tax + totalAmount)*100.0)/100.0);
        System.out.println("    Total Amount: " + totalAmount);

        System.out.println("==============================================================================");
        System.out.println("                               MODE OF PAYMENT                                ");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("                               [1] CASH                                       ");
        System.out.println("                               [2] CREDIT/DEBIT CARD                          ");
        System.out.println("------------------------------------------------------------------------------");
        int mop = Console.getInt("    Enter MOP: ",1,2);

        switch(mop) {
            case 1:
                cashPayment(invoice);
                break;

            case 2:
                cardPayment(invoice);
                break;
        }
    }


    /**
     * Function for the Discount
     */
    private static void discount(Invoice invoice)
    {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("                                Discount Type                                 ");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("                               [1] PWD                                        ");
        System.out.println("                               [2] Senior Citizen                             ");
        System.out.println("------------------------------------------------------------------------------");
        int discountType = Console.getInt("    Enter discount type: ",1, 2);
        switch (discountType) {
            case 1 -> {
                totalAmount = invoice.getTotal() - (0.20 * invoice.getTotal());
                System.out.println("                        PWD Discount Applied! ");
            }
            case 2 -> {
                totalAmount = invoice.getTotal() - (0.20 * invoice.getTotal());
                System.out.println("                      Senior Citizen Discount Applied! ");
            }
        }
    }


    /**
     * Function for Cash Payment
     */
    private static void cashPayment(Invoice invoice)
    {
        System.out.println("------------------------------------------------------------------------------");
        do {
            cash = Console.getDouble("    Enter cash: ");
            change = cash - totalAmount;
        } while(cash < totalAmount);
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("    Total Amount: " + totalAmount);
        change = (Math.round(change * 100.0)/100.0);
        System.out.println("    Change: " + change);
        System.out.println("------------------------------------------------------------------------------");
        System.out.print("    Print the receipt? (y/n) ");
        String print = sc.next();

        if(print.equalsIgnoreCase("Y"))
        {
            Cashier.displayInvoiceCash(invoice);
        }
        System.out.println("------------------------------------------------------------------------------");
    }


    /**
     * Function for Card Payment
     */
    private static void cardPayment(Invoice invoice)
    {
        boolean cardValid;
        System.out.println("------------------------------------------------------------------------------");
        cardLabel = Console.getString("    Enter EMV Card Label: ");
        cardHolder = Console.getString("    Enter Card Holder: ");

        do {
            System.out.print("    Enter Card number: ");
            String cardNumber = sc.next();

            cardValid = CreditCardValidator.validateCreditCardNumber(cardNumber);
        } while (!cardValid);

        System.out.println("                              Payment Successfully!");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("    Total Amount: " + totalAmount);
        String printCC = Console.getString("    Print the receipt: (y/n) ");
        if(printCC.equalsIgnoreCase("Y"))
        {
            Cashier.displayInvoiceCC(invoice);
        }
        System.out.println("------------------------------------------------------------------------------");
    }


    /**
     * Function for getting the orders
     */
    public static void getLineItems(Invoice invoice)
    {
        Admin ad = new Admin();
        ad.displayData();
        String choice = "y";
        while (choice.equalsIgnoreCase("y"))
        {
            System.out.println("                                    ORDERS                                    ");
            System.out.println("------------------------------------------------------------------------------");
            int menuCode = Console.getInt("    Enter product code: ");
            int quantity = Console.getInt("    Enter quantity: ");

            Menu menu = MenuDB.getMenu(menuCode);
            invoice.addItem(new LineItem(menu, quantity));

            choice = Console.getString("    Add another order? (y/n): ");
            System.out.println("------------------------------------------------------------------------------");
        }
    }


    /**
     * Invoice for the Cash Payment
     */
    private static void displayInvoiceCash(Invoice invoice)
    {
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm a");
        System.out.println("\n\n\n\n\n\n\n");
        System.out.println("======================================================\n");
        System.out.println("                CHAMPAS MEXICAN GRILL                 ");
        System.out.println("             Ground Floor, North West Wing            ");
        System.out.println("            SM City Clark, Angeles City,Ph            ");
        System.out.println("                    0998 530 9128                    \n");
        System.out.println("           Trans. Date: " + dtf.format(localDate)+"\n");
        System.out.println("------------------------------------------------------");
        System.out.printf(String.format("%-20s %-12s %-7s %-6s\n","Item", "Price", "Qty", "Total"));
        System.out.println("------------------------------------------------------");
        for (LineItem lineItem : invoice.getLineItems())
        {
            Menu menu = lineItem.getMenu();

            System.out.printf(String.format("%-20s %-12s  %-7s%-6s\n"
                    , menu.getMenu_item()
                    , menu.getPriceFormatted()
                    , lineItem.getQuantity()
                    , lineItem.getTotalFormatted()));
        }
        System.out.println("------------------------------------------------------");
        System.out.println("\t\t\t\t\t\tSubtotal:      \tPhp " +subTotal);
        System.out.println("\t\t\t\t\t\tVAT (12%):     \tPhp " +tax);
        System.out.println("\t\t\t\t\t\tTotal Amount:  \tPhp " +totalAmount);
        System.out.println("\t\t\t\t\t\tCash tendered: \tPhp " +cash);
        System.out.println("\t\t\t\t\t\tChange:        \tPhp " +change);
        System.out.println("\n\n");
        System.out.println("                THANK YOU FOR VISITING!");
        System.out.println("        Gwn Software Labs Inc., 67 Boston Square");
        System.out.println("             Bldg. B Rhodes, Albany, NY");
        System.out.println("            Accreditation: 76859494A8837k");
        System.out.println();
        System.out.println("======================================================");
        System.out.println("\n\n\n\n\n\n\n");
    }


    /**
     * Invoice for the Credit/Debit Card Payment
     */
    private static void displayInvoiceCC(Invoice invoice)
    {
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm a");
        System.out.println("\n\n\n\n\n\n\n");
        System.out.println("======================================================\n");
        System.out.println("                CHAMPAS MEXICAN GRILL                 ");
        System.out.println("             Ground Floor, North West Wing            ");
        System.out.println("            SM City Clark, Angeles City,Ph            ");
        System.out.println("                    0998 530 9128                    \n");
        System.out.println("           Trans. Date: " + dtf.format(localDate)+"\n");
        System.out.println("------------------------------------------------------");
        System.out.printf(String.format("%-20s %-8s %-5s %-5s\n","Item", "Price", "Qty", "Total"));
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
        System.out.println("\t\t\t\t\tSubtotal:      \tPhp " +subTotal);
        System.out.println("\t\t\t\t\tVAT (12%):     \tPhp " +tax);
        System.out.println("\t\t\t\t\tTotal Amount:  \tPhp " +totalAmount);
        System.out.println("------------------------------------------------------");
        System.out.println("    EMVP Card Label: "+ cardLabel);
        System.out.println("    RESP:            Approved");
        System.out.println("    Cardholder:      "+ cardHolder);
        System.out.println("\n");
        System.out.println("                THANK YOU FOR VISITING!");
        System.out.println("        Gwn Software Labs Inc., 67 Boston Square");
        System.out.println("             Bldg. B Rhodes, Albany, NY");
        System.out.println("            Accreditation: 76859494A8837k");
        System.out.println();
        System.out.println("======================================================");
        System.out.println("\n\n\n\n\n\n\n");
    }
}
