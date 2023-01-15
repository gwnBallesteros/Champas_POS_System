import java.text.NumberFormat;
import java.util.Locale;

public class Menu
{
    private int menu_id;
    private String menu_code;
    private String menu_item;
    private double menu_price;

    public Menu()
    {
        this.menu_id = 0;
        this.menu_code = "";
        this.menu_item = "";
        this.menu_price = 0.0;
    }
    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_code() {
        return menu_code;
    }

    public void setMenu_code(String menu_code) {
        this.menu_code = menu_code;
    }

    public String getMenu_item() {
        return menu_item;
    }

    public void setMenu_item(String menu_item) {
        this.menu_item = menu_item;
    }

    public double getMenu_price() {
        return menu_price;
    }

    public void setMenu_price(double menu_price) {
        this.menu_price = menu_price;
    }

    public String getPriceFormatted()
    {
        NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
        return currency.format(getMenu_price());
    }
}
