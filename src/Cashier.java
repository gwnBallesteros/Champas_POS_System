import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Cashier
{
    public static Scanner sc = new Scanner(System.in);
    private static double amount = 0.0;
    private static int quantity = 0;
    private static double totalAmount = 0;
    private static double change = 0.0;
    private static double cash = 0.0;
    private static double tax = 0.0;
    private static  double subTotal = 0.0;
    private static String cardLabel;
    private static String cardHolder;

    public static void totalOrders(Invoice invoice)
    {
        //Cashier.getLineItems(invoice);
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
        System.out.printf(String.format("%77s","Subtotal:   "+invoice.getTotalFormatted()+"\n"));
        //System.out.println("\t\t\t\t\tSubtotal: \t" + invoice.getTotalFormatted()+"\n");
        totalAmount = invoice.getTotal();
        quantity = (int) invoice.getTotalQuantity();
//        System.out.println("Amount:   " + amount );
        System.out.println("    Quantity: " + quantity );
        System.out.println("==============================================================================");
    }

    public static void paymentInput(Invoice invoice)
    {

        System.out.println("==============================================================================");
        System.out.println("                                  DISCOUNT                                    ");
        System.out.println("------------------------------------------------------------------------------");
        System.out.print("    Discount (if applicable): ");
        String discount = sc.nextLine();

        if(discount.equalsIgnoreCase("Y"))
        {
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("                                Discount Type                                 ");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("                               [1] PWD                                        ");
            System.out.println("                               [2] Senior Citizen                             ");
            System.out.println("------------------------------------------------------------------------------");
            System.out.print("    Enter discount type: ");
            int discountType = sc.nextInt();
            switch(discountType)
            {
                case 1:
                    totalAmount = invoice.getTotal() - (0.20 * invoice.getTotal());
                    System.out.println("                        PWD Discount applied! ");
                    break;
                case 2:
                    totalAmount = invoice.getTotal() - (0.25 * invoice.getTotal());
                    System.out.println("                      Senior Citizen Discount applied! ");
                    break;
            }

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
        int mop = Console.getInt("    Enter MOP: ");

        switch(mop) {
            case 1:
                System.out.println("------------------------------------------------------------------------------");
                do {
                    System.out.print("    Enter cash: ");
                    cash = sc.nextDouble();
                    change = cash - totalAmount;
                } while(cash < totalAmount);
                System.out.println("------------------------------------------------------------------------------");
                System.out.println("    Total Amount: " + totalAmount);
                change = (Math.round(change * 100.0)/100.0);
                System.out.println("    Change: " + change);
                System.out.println("------------------------------------------------------------------------------");
                System.out.print("    Print the receipt: ");
                String print = sc.next();

                if(print.equalsIgnoreCase("Y"))
                {
                    Cashier.displayInvoiceCash(invoice);
                }

                break;

            case 2:
                System.out.println("------------------------------------------------------------------------------");
                boolean cardValid;
                System.out.print("    Enter EMV Card label: ");
                cardLabel = sc.nextLine();
                System.out.print("    Enter Card holder: ");
                cardHolder = sc.nextLine();

                do {
                    System.out.print("    Enter Card number: ");
                    String cardNumber = sc.next();

                    cardValid = CreditCardValidator.validateCreditCardNumber(cardNumber);
                } while (cardValid == false);

                System.out.println("                              Payment Successfully!");
                System.out.println("------------------------------------------------------------------------------");
                System.out.println("    Total Amount: " + totalAmount);
                String printCC = Console.getString("    Print the receipt: (y/n) ");
//                System.out.print("    Print the receipt: ");
//                String printCC = sc.next();

                if(printCC.equalsIgnoreCase("Y"))
                {
                    Cashier.displayInvoiceCC(invoice);
                }
                break;
        }
    }

    public static void getLineItems(Invoice invoice)
    {
        Admin ad = new Admin();
        ad.displayData();
        String choice = "y";
        while (choice.equalsIgnoreCase("y"))
        {
            System.out.println("                                    ORDERS                                    ");
            System.out.println("------------------------------------------------------------------------------");
            String menuCode = Console.getString("    Enter product code: ");
            int quantity = Console.getInt("    Enter quantity: ");

            Menu menu = MenuDB.getMenu(menuCode);
            invoice.addItem(new LineItem(menu, quantity));

            choice = Console.getString("    Add another order? (y/n): ");
            System.out.println("------------------------------------------------------------------------------");
        }
    }

    public static void displayInvoiceCash(Invoice invoice)
    {
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/YYYY h:mm a");
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
        System.out.println("\t\t\t\t\tCash tendered: \tPhp " +cash);
        System.out.println("\t\t\t\t\tChange:        \tPhp " +change);
        System.out.println("\n\n");
        System.out.println("                THANK YOU FOR VISITING!");
        System.out.println("        Gwn Software Labs Inc., 67 Boston Square");
        System.out.println("             Bldg. B Rhodes, Albany, NY");
        System.out.println("            Accreditation: 76859494A8837k");
        System.out.println();
        System.out.println("======================================================");
    }

    public static void displayInvoiceCC(Invoice invoice)
    {
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/YYYY h:mm a");
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
    }
}
