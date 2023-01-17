/**
 * Constructors for the Menu
 */

public class Menu
{
    private int menu_id;
    private int menu_code;
    private String menu_item;
    private String menu_status;
    private double menu_price;

    public int getMenu_code() {
        return menu_code;
    }

    public void setMenu_code(int menu_code) {
        this.menu_code = menu_code;
    }

    public Menu()
    {
        this.menu_id = 0;
        this.menu_code = 0;
        this.menu_item = "";
        this.menu_status = "";
        this.menu_price = 0.0;
    }
    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_status() {
        return menu_status;
    }

    public void setMenu_status(String menu_status) {
        this.menu_status = menu_status;
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
        String s = "Php " + getMenu_price();
        return s;
    }
}
