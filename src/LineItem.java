import java.text.NumberFormat;
import java.util.Locale;

public class LineItem
{
    private Menu menu;
    private int quantity;

    public LineItem()
    {
        this.menu = null;
        this.quantity = 0;
    }

    public LineItem(Menu menu, int quantity)
    {
        this.menu = menu;
        this.quantity = quantity;
    }

    public void setMenu(Menu menu)
    {
        this.menu = menu;
    }

    public Menu getMenu()
    {
        return menu;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public double getTotal()
    {
        double total = menu.getMenu_price() * quantity;
        return total;
    }

    public String getTotalFormatted()
    {
        NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
        return currency.format(this.getTotal());
    }
}
