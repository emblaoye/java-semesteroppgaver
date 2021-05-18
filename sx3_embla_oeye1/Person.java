public class Person {
    private String name;
    private Address address;

    //Constructor
    public Person(String name, Address address){
        this.name = name;
        this.address = address;
    }

    //toString
    public String toString(){
        return name + "\n" + address;
    }
}
