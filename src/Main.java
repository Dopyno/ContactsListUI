import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Contacts> contacts = new ArrayList<>();
    private static File file = new File("ContactList.txt");
   private static ObjectOutputStream oos = null;  // Create an external file list to store our list
   private static ObjectInputStream ois = null;  // Create method to read and load external list
    private static ListIterator listIterator = null;  // Declare a list iterator to sort our contact list

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        if(file.isFile()){
            ois = new ObjectInputStream(new FileInputStream(file));  //Check if our file is existing(created) and load it. (ObjectInputStream)- to read data
            contacts = (ArrayList<Contacts>)ois.readObject();   // pharse our Arraylist Contact
            ois.close();
        }
        int answer = 0;
        welcomeMessage();

        while (answer != 6) {
            displayMessage();
            answer = scanner.nextInt();
            scanner.nextLine();

            switch (answer){
                case 1:                      // Search contact
                    searchContact();
                    break;
                case 2:                     //  Add new contact
                     addContact();
                    break;
                case 3:                      //   Print list of contact
                    displayContacts();
                    break;
                case 4:                      //  Update an existing contact
                    updateContacts();
                    break;
                case 5:                      //   Remove contact
                    removeContact();
                    break;
                case 6:                       //  Quit
                    messageToQuit();
                    break;
                case 7:                       //  Quit
                    deleteAllDataContact();
                    break;
            }
        }
    }

    private static void displayMessage(){
        System.out.println("Please choose an action: ");
        System.out.println("\t 1. Search contact.");
        System.out.println("\t 2. Add new contact.");
        System.out.println("\t 3. Print list of contact.");
        System.out.println("\t 4. Update an existing contact.");
        System.out.println("\t 5. Remove contact.");
        System.out.println("\t 6. Quit");
        System.out.println("\t 7. Delete All Contact List.");
    }
    private static void message(String message){
        System.out.println(message);
    }
    private static void messageToQuit(){
        System.out.println("Thank you for using our contact list!");
    }
    private static void addContact() throws IOException {
        String name = "";
        while (!name.toLowerCase().equals("exit")) {
            System.out.print("Enter your name or type EXIT to stop: ");
            name = scanner.nextLine();
            if (name.toLowerCase().equals("exit")) {
                break;
            }
            System.out.print("Enter your phone number: ");
            String phone = scanner.nextLine();
            contacts.add(new Contacts(name, phone));

            System.out.println("Contact added successfully!\n");
        }
        oos = new ObjectOutputStream(new FileOutputStream(file));   // After Contact list was created by user is time to store data in that File.txt
        oos.writeObject(contacts);                                  // ObjectOutputStream is created
        oos.close();
    }
    private static void displayContacts() throws IOException{

        if (file.isFile()) {
            oos = new ObjectOutputStream(new FileOutputStream(file));   // After Contact list was created by user is time to store data in that File.txt
            oos.writeObject(contacts);                                  // ObjectOutputStream is created
            oos.close();
//        System.out.println("---------------------------------------------------------");
//       for(int i = 0; i < contacts.size(); i++){
//           System.out.println((i + 1) + ". " + contacts.get(i).getName() + " - " + contacts.get(i).getPhoneNo());
//       }
            System.out.println("---------------------------------------------------------");

            listIterator = contacts.listIterator();             //List iterator function
            while (listIterator.hasNext()) {
                System.out.println(listIterator.next());
            }
            System.out.println("---------------------------------------------------------");
        } else {
            System.out.println("File not found...!");
        }

    }
     private static void welcomeMessage(){
         System.out.println("******************************************************");
         System.out.println("   *****    Welcome to your contact app!    ******");
         System.out.println("******************************************************");
     }
     private static void removeContact()throws IOException{
        if (file.isFile()) {
            oos = new ObjectOutputStream(new FileOutputStream(file));   // After Contact list was created by user is time to store data in that File.txt
            oos.writeObject(contacts);                                  // ObjectOutputStream is created
            oos.close();
            System.out.println("---------------------------------------------------------");
            for (int i = 0; i < contacts.size(); i++) {
                System.out.println((i + 1) + ". " + contacts.get(i).getName() + " - " + contacts.get(i).getPhoneNo());
            }
            System.out.println("---------------------------------------------------------");
            System.out.println("Please select element you want to delete: ");
            int selection = scanner.nextInt();
            scanner.nextLine();

            System.out.println("You choose to delete contact no." + selection + " - " + contacts.get(selection - 1));
            System.out.println("Please select [ Y / N]");

            String answer = scanner.nextLine();
            if (answer.toUpperCase().equals("Y")) {
                contacts.remove(selection - 1);
                System.out.println("Contact deleted....!");
                oos = new ObjectOutputStream(new FileOutputStream(file));   // After Contact list was created by user is time to store data in that File.txt
                oos.writeObject(contacts);                                  // ObjectOutputStream is created
                oos.close();
            } else {
                System.out.println("Ok, contact is not deleted...!");
            }
        }else {
            System.out.println("File not found...!");
        }


     }
     private static void updateContacts() throws IOException{
        if(file.isFile()) {
            oos = new ObjectOutputStream(new FileOutputStream(file));   // After Contact list was created by user is time to store data in that File.txt
            oos.writeObject(contacts);                                  // ObjectOutputStream is created
            oos.close();
            System.out.println("---------------------------------------------------------");
            for (int i = 0; i < contacts.size(); i++) {
                System.out.println((i + 1) + ". " + contacts.get(i).getName() + " - " + contacts.get(i).getPhoneNo());
            }
            System.out.println("---------------------------------------------------------");

            System.out.println("Please select element you want to update: ");
            int selection = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter your new name: ");
            String name = scanner.nextLine();
            System.out.print("Enter your new phone number: ");
            String phone = scanner.nextLine();

                contacts.set((selection - 1), new Contacts(name, phone));
                oos = new ObjectOutputStream(new FileOutputStream(file));   // After Contact list was created by user is time to store data in that File.txt
                oos.writeObject(contacts);                                  // ObjectOutputStream is created
                oos.close();
                System.out.println("Contact updated successfully ...!");
        }else {
            System.out.println("File not found...!");
        }
     }
     private static void searchContact() throws IOException{
         if (file.isFile()) {
             oos = new ObjectOutputStream(new FileOutputStream(file));   // After Contact list was created by user is time to store data in that File.txt
             oos.writeObject(contacts);                                  // ObjectOutputStream is created
             oos.close();

             message("Enter name to be search: ");
             String name = scanner.nextLine();

             for (int i = 0; i < contacts.size(); i++) {
                 if (contacts.get(i).getName().equals(name)) {
                     System.out.println("Contact found: " + contacts.get(i).getName() + " - " + contacts.get(i).getPhoneNo());
                     break;
                 } else {
                     System.out.println("Contact not found...!");
                 }
             }
         }else {
             System.out.println("File not found...!");
         }
     }
     private static void deleteAllDataContact()throws IOException{
        if(file.isFile()){
            oos = new ObjectOutputStream(new FileOutputStream(file));   // After Contact list was created by user is time to store data in that File.txt
            oos.writeObject(contacts);                                  // ObjectOutputStream is created
            oos.close();
            message("You choose to delete all your data from Contact list...!");
            System.out.println("Please select [ Y / N]");
            String answer = scanner.nextLine();
            if (answer.toUpperCase().equals("Y")) {
                oos = new ObjectOutputStream(new FileOutputStream(String.valueOf(file.delete())));
                oos.writeObject(contacts);
                oos.close();
                message("All your data was deleted...!");
            }else {
                System.out.println("Ok, contact list is not deleted...!");
            }
        }else {
            System.out.println("File not found...!");
        }
     }
}
