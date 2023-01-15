import java.text.NumberFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;

public class Invoice extends Cashier
{
    private List <LineItem> lineItems;

    public Invoice()
    {
        lineItems = new ArrayList<>();
    }

    public void addItem(LineItem lineItem)
    {
        lineItems.add(lineItem);
    }

    public List<LineItem> getLineItems()
    {
        return lineItems;
    }

    public double getTotal()
    {
        double invoiceTotal = 0;
        for(LineItem lineItem : lineItems)
        {
            invoiceTotal += lineItem.getTotal();
        }

        return invoiceTotal;
    }

    public double getTotalQuantity()
    {
        int totalQuantity = 0;
        for(LineItem lineItem : lineItems)
        {
            totalQuantity += lineItem.getQuantity();
        }

        return totalQuantity;
    }

    public String getTotalFormatted()
    {
        NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
        return currency.format(getTotal());
    }
}
