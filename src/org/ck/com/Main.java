package org.ck.com;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Contact> contacts;
    private static Scanner scanner;
    private static int id=0;

    public static void main(String[] args) {



        contacts=new ArrayList<>();
        System.out.println("Welcome to my world of programming");
        showInitialOptions();
    }
    private static void showInitialOptions(){
        System.out.println("Please select one"+
                "\n\t1. Manage contacts"+
                "\n\t2. Messages"+
                "\n\t3. Quit");
        scanner=new Scanner(System.in);
        int choice= scanner.nextInt();

        switch(choice){

            case 1:
                manageContacts();
                break;
            case 2:
                manageMessages();
            default:
                break;
        }
    }

    private static void manageMessages() {
        System.out.println("Please select one:"+
                "\n\t1.Show all messages"+
                "\n\t2. Send a new message" +
                "\n\t3. Go back");
        int choice= scanner.nextInt();
        switch (choice){
            case 1:
                showAllMessages();
                break;
            case 2:
                sendNewMessage();
                break;
            default:
                break;
        }



    }

    private static void sendNewMessage() {
        System.out.println("Who are you going to send the message?");
        String name=scanner.next();
        if (name.equals("")){
            System.out.println("Please enter a name!");
            sendNewMessage();
        }
        else{
            Boolean doesExist=false;
            for (Contact c:contacts){
                if(c.getName().equals(name)){
                    doesExist=true;
                }
            }
            if(doesExist){
                System.out.println("Type Your message");
                String text=scanner.next();
                if(text.equals("")){
                    System.out.println("please enter some message");
                    sendNewMessage();
                }
                else{
                    id++;
                    Message newMessage= new Message(text,name,id);
                    for (Contact c: contacts){
                        if(c.getName().equals(name)){
                            ArrayList<Message> newMessages= c.getMessages();
                            newMessages.add(newMessage);
                            c.setMessages(newMessages);


                        }
                    }
                }
            }
            else{
                System.out.println("There is no such contact");
            }
        }
        showInitialOptions();
    }

    private static void showAllMessages() {
        ArrayList<Message> allMessages= new ArrayList<>();
        for (Contact c:contacts){
            allMessages.addAll(c.getMessages());
        }
        if(allMessages.size()>0){
            for (Message m:allMessages){
                m.getDetails();
                System.out.println("#######################");
            }
        }
        else{
            System.out.println("You do not have any message");
        }
        showInitialOptions();
    }

    private static void manageContacts(){
        System.out.println("Please select one:"+
                "\n\t1. Show all Contacts"+
                "\n\t2. Add a new contact"+
                "\n\t3. Search for a contact" +
                "\n\t4. Delete a contact"+
                "\n\t5. Go back");
        int choice=scanner.nextInt();
        switch (choice){
            case 1:
                showAllContacts();
                break;
            case 2:
                addNewContact();
                break;
            case 3:
                searchForContact();
                break;
            case 4:
                deleteContact();
                break;
            default:
                showInitialOptions();
                break;

        }
    }

    private static void deleteContact() {
        System.out.println("Please enter the name:");
        String name= scanner.next();
        if (name.equals("")){
            System.out.println("please enter a name!");
            deleteContact();
        }
        else{
            Boolean doesExist=false;
            for (Contact c: contacts){
                if (c.getName().equals(name)){
                    doesExist=true;
                    contacts.remove(c);
                }
            }
            if (!doesExist){
                System.out.println("There is no such Contact in your phone");
            }
        }
        showInitialOptions();
    }

    private static void searchForContact() {
        System.out.println("please enter the contact name:");
        String name=scanner.next();
        if (name.equals("")){
            System.out.println("please enter the name!");
            searchForContact();
        }
        else{
            Boolean doesExist=false;
            for (Contact c:contacts){
                if(c.getName().equals(name)){
                    doesExist=true;
                    c.getDetails();
                }
            }

            if (!doesExist){
                System.out.println("There is no such contact in your phone");
            }
        }
        showInitialOptions();
    }

    private static void addNewContact() {
        System.out.println("adding a  new contact...." +
                "\nPlease Enter the contacts name:");
        String name= scanner.next();
        System.out.println("please enter the contact number:");
        String number=scanner.next();
        System.out.println("please enter the contact's email:");
        String email=scanner.next();

        if (name.equals("") || number.equals("") || email.equals("")){
            System.out.println("Please enter all of the information");
            addNewContact();
        }
        else{
            Boolean doesExist=false;
            for(Contact c:contacts){
                if (c.getName().equals(name)){
                    doesExist=true;
                }
            }
            if(doesExist){
                System.out.println("We already have a contact "+name +" with this name");
            }
            else{
                Contact contact= new Contact(name, number, email);
                contacts.add(contact);
            }
            System.out.println(name + " added successfully");
        }
        showInitialOptions();

    }

    private static void showAllContacts(){
        for (Contact c:contacts) {
            c.getDetails();

            System.out.println("#####################################");
        }
        showInitialOptions();
    }

}
