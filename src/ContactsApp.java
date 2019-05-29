import java.util.Scanner;

public class ContactsApp {
    public static void main(String[] args) {

        userInterface();

    }

    public static void userInterface(){
            Scanner sc = new Scanner(System.in);


        System.out.println("                       \t  .--.\n" +
                "                          |  |\n" +
                "                          |  |\n" +
                "                          |  |\n" +
                "                          |  |\n" +
                "         _.-----------._  |  |\n" +
                "      .-'      __       `-.  |\n" +
                "    .'       .'  `.        `.|\n" +
                "   ;         :    :          ;\n" +
                "   |         `.__.'          |\n" +
                "   |   ___                   |\n" +
                "   |  (_M_) M O T O R A L A  |\n" +
                "   | .---------------------. |\n" +
                "   | | 1. View contacts.   | |\n" +
                "   | | 2. Add new contact. | |\n" +
                "   | | 3. Search contact.  | |\n" +
                "   | | 4. Delete contact.  | |\n" +
                "   | | 5. Exit.            | |\n" +
                "   | |                     | |\n" +
                "   | |                     | |\n" +
                "   | `---------------------' |\n" +
                "   |                         |\n" +
                "   |                __       |\n" +
                "   |  ________  .-~~__~~-.   |\n" +
                "   | |___C___/ /  .'  `.  \\  |\n" +
                "   |  ______  ;   : OK :   ; |\n" +
                "   | |__A___| |  _`.__.'_  | |\n" +
                "   |  _______ ; \\< |  | >/ ; |\n" +
                "   | [_=\n");


//            System.out.println("\n1. View contacts.\n" +
//                    "2. Add a new contact.\n" +
//                    "3. Search a contact by name.\n" +
//                    "4. Delete an existing contact.\n" +
//                    "5. Exit.\n" +
//                    "Enter an option (1, 2, 3, 4 or 5):\n");

            int choice = sc.nextInt();
        switch(choice){

            case 1:
                ShowContacts.showContacts(ShowContacts.directory, ShowContacts.filename);
                userInterface();
                break;
            case 2:
                ShowContacts.addContact(ShowContacts.directory, ShowContacts.filename);
                userInterface();
                break;
            case 3:
                System.out.println(ShowContacts.searchContact(ShowContacts.directory, ShowContacts.filename));
                userInterface();
                break;
            case 4:
                ShowContacts.deleteContact(ShowContacts.directory, ShowContacts.filename);
                userInterface();
                break;
            case 5:
                System.out.println("goodbye");
                System.exit(0);
            default:
                userInterface();
        }
    }
}
