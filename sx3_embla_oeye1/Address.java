public class Address {
    private String street;
    private int streetNumber;
    private int postalCode;
    private String town;
    private String country;

    //Constructor
    public Address (String street, int streetNumber, int postalCode, String town, String country) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.town = town;
        this.country = country;
    }

    //toString
    public String toString(){
      return street + " " + streetNumber + "\n" + postalCode + ", " + town + "\n" + country;
    }
}


