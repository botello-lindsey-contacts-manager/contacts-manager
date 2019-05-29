import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ShowContacts {

    public static String directory = "data";
    public static String filename = "contacts.txt";

    public static void showContacts(String directory, String filename){
        Path file = Paths.get(directory, filename);
        try {
            List<String> contactList = Files.readAllLines(file);
            System.out.println("Name | Phone number\n" +
                    "-----------------");
            for(String contact: contactList){
                String name = contact.split(" = ")[0];
                String number = contact.split(" = ")[1];

                System.out.println(name + " | " + number);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addContact(String directory, String filename){
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter a name:");
        String name = sc.nextLine();
        System.out.println("please enter a phone number");
        String number = sc.nextLine();

        number = number.substring(0,3) + "-" + number.substring(3, 6) + "-" + number.substring(6);

        String contact = name + " = " + number;
        ArrayList<String> list = new ArrayList<>(List.of(contact));
        try {
            Files.write(
                    Paths.get(directory, filename),
                    list,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String searchContact(String directory, String filename){
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter a name:");
        String contact = sc.nextLine();

        Path file = Paths.get(directory, filename);
            String output = "Contact not found";
        try {
            List<String> contactList = Files.readAllLines(file);
            for(String item: contactList){
                if(item.contains(contact)){
                    String name = item.split("-")[0];
                    String number = item.split("-")[1];
                    return output = name + " | " + number;
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        } return output;
    }


    public static void deleteContact(String directory, String filename){
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter the name of the contact that you would like to remove:");
        String contact = sc.nextLine();

       try {
           List<String> lines = Files.readAllLines(Paths.get(directory, filename));
           List<String> updatedList = new ArrayList<>();

           for (String line : lines) {
               if (!line.contains(contact)) {
                   updatedList.add(line);
               }

           }
           if (! updatedList.contains(contact)){
               System.out.println("\nContact not found\n");
           }

           Files.write(Paths.get(directory, filename), updatedList);

       } catch (IOException e) {
           e.printStackTrace();
       }

       showContacts(directory, filename);
    }


    /*public static void main(String[] args) {


        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, filename);
        if (Files.notExists(dataFile)) {
            try {
                Files.createDirectories(dataDirectory);
            } catch(IOException e){
                e.printStackTrace();
            }
        }

        if (! Files.exists(dataFile)) {

            try {
                Files.createFile(dataFile);
            } catch(IOException e){
                e.printStackTrace();
            }
        }

        ArrayList<String> contacts = new ArrayList<>(List.of("Nadia - 1234567890", "jason - 0987654321"));


        addContact(directory, filename, contacts);



        Scanner sc = new Scanner(System.in);

        System.out.println("who do you want to search?");
        String search = sc.nextLine();

        System.out.println(searchContact(directory, filename, search));

        System.out.println("who do you want to redact?");
        search = sc.nextLine();

        deleteContact(directory, filename, search);*/







}
