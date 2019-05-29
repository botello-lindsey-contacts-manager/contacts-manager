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

        if (searchContact(directory, filename, name)) {

            System.out.printf("There's already a contact named %s. Do you want to overwrite it? (Yes/No)", name);
            String answer = sc.nextLine();

            if (answer.equalsIgnoreCase("Yes")) {
                overwriteContact(directory, filename, name, contact);
                System.out.println("You have successfully altered your contact.");
            }

        } else {
            ArrayList<String> list = new ArrayList<>(List.of(contact));
        try {
            Files.write(
                    Paths.get(directory, filename),
                    list,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            e.printStackTrace();
        } }

    }


    public static void overwriteContact(String directory, String filename, String name, String contact){

        List<String> lines = null;

        try {
            lines = Files.readAllLines(Paths.get(directory, filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> newList = new ArrayList<>();

        for (String line : lines) {
            if (line.contains(name)) {
                newList.add(contact);
                continue;
            }
            newList.add(line);
        }

        try {
            Files.write(Paths.get(directory, filename), newList);
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
                    String name = item.split("=")[0];
                    String number = item.split("=")[1];
                    return output = name + " | " + number;
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        } return output;
    }

    public static Boolean searchContact(String directory, String filename, String contact){

        Path file = Paths.get(directory, filename);
        Boolean output = true;

        try {
            List<String> contactList = Files.readAllLines(file);
            for(String item: contactList){
                if(item.contains(contact)){
                    return output;
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        } return !output;

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



}
