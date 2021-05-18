import java.util.UUID;

public class Parcel {
    private Person sender;
    private Person recipient;
    private UUID barcode;

    //Constructor
    public Parcel(Person sender, Person recipient){
        this.sender = sender;
        this.recipient = recipient;
        barcode = UUID.randomUUID();
    }

    //toString
    public String toString(){
        return "Barcode: " + barcode + "\n" + "Sender: " + sender + "\n" + "Recipient: " + recipient;
    }
}
