import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class PostalService {
    public static void main(String[] args) {

        ArrayList<Parcel> registeredParcels = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        boolean done = false;
        while (!done) {
            System.out.printf("Enter command (%d parcel(s) registered)"
                    + "%nr: register parcel"
                    + "%np: print parcels to display"
                    + "%nw: write parcels to file"
                    + "%nc: clear parcel queue"
                    + "%nq: quit%n> ", registeredParcels.size());

            String command = sc.next();

            if (command.equals("r")) {
                Parcel parcel = registerParcel();
                registeredParcels.add(parcel);
                System.out.println("[Parcel registered]");

            } else if (command.equals("p")) {
                parcelsToTerminal(registeredParcels);

            } else if (command.equals("w")) {
                try {
                    System.out.println("Enter filename: ");
                    String filename = sc.next();
                    parcelsToFile(filename, registeredParcels);
                } catch (FileNotFoundException e) {
                    System.out.println("[Cannot write to file]");
                }

            } else if (command.equals("c")) {
                    System.out.printf("Are you sure you want to delete all: %d parcel(s)?\n"
                            + "YES/NO\n", registeredParcels.size());
                    String answer = sc.next();
                    answer = answer.toUpperCase();

                    if (answer.equals("YES")) {
                        registeredParcels.clear();
                        System.out.println("[Cleared registered parcels]");
                    } else if (answer.equals("NO")) {
                        System.out.println("[Did not clear registered parcels]");
                    } else {
                        System.out.println("[Unknown command]");
                    }

            } else if (command.equals("q")) {
                System.out.println("[Quitting]");
                done = true;

            } else {
                System.out.println("[Unknown command]");
            }
        }
    }

    //Registrerer pakker
    public static Parcel registerParcel() {

        //Navn og addresse til senderen
        System.out.println("Register sender\n--------------------");
        Person sender = declarePerson();

        //Navn og adresse til mottaker
        System.out.println("Register recipient\n--------------------");
        Person recipient = declarePerson();

        return new Parcel(sender, recipient);
    }

    //Oppretter en Person
    public static Person declarePerson() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter person name:");
        String name = sc.nextLine();
        System.out.println("Enter street name:");
        String street = sc.nextLine();
        System.out.println("Enter street number:");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input, street number must be an integer ");
            sc.next();
        }
        int streetNumber = sc.nextInt();
        System.out.println("Enter postal code:");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input, postal code must be an integer ");
            sc.next();
        }
        int postalCode = sc.nextInt();
        sc.nextLine(); //To clear newline from nextInt
        System.out.println("Enter town:");
        String town = sc.nextLine();
        System.out.println("Enter country:");
        String country = sc.nextLine();
        Address address = new Address(street, streetNumber, postalCode, town, country);

        return new Person(name, address);
    }

    //Printer alle registrerte pakker til terminalen
    public static void parcelsToTerminal(ArrayList<Parcel> registeredParcels) {

        for (Parcel p:registeredParcels) {
            System.out.println(p);
        }
    }

    //Skriver listen med registrerte pakker til en fil
    public static void parcelsToFile(String filename, ArrayList<Parcel> registeredParcels)
            throws FileNotFoundException {

        PrintWriter file = new PrintWriter(filename);
        for (Parcel p:registeredParcels) {
            file.println(p);
        }
        file.close();
        System.out.println("[Parcels written to file]");
    }
}


