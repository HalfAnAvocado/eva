package servlets.pizza;

public class Order
{
    private String kundenname;
    private String kundennummer;
    private String lieferadresse;
    private String pizza;
    
    public Order(String kundenname, String kundennummer, String lieferadresse, String pizza)
    {
        super();
        this.kundenname = kundenname;
        this.kundennummer = kundennummer;
        this.lieferadresse = lieferadresse;
        this.pizza = pizza;
    }
    
    public String getKundenname()
    {
        return kundenname;
    }
    
    public String getKundennummer()
    {
        return kundennummer;
    }
    
    public String getLieferadresse()
    {
        return lieferadresse;
    }
    
    public String getPizza()
    {
        return pizza;
    }
}
