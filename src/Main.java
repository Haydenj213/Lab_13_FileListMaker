import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<String> myArrList = new ArrayList<>();
    static Scanner in = new Scanner(System.in);

    static boolean needsToBeSaved = false;

    public static void main(String[] args) {
        boolean options = true;

        while (options) {
            displayOptions();
            String option = SafeInput.getRegExString(in, "Please enter your menu choice", "[AaDdIiVvQqMmOoSsCc]").toUpperCase();

            switch (option) {
                case "A" -> addItem();
                case "D" -> deleteItem();
                case "I" -> insertItem();
                case "M" -> moveItem();
                case "O" -> openList();
                case "S" -> saveList();
                case "C" -> clearList();
                case "V" -> viewList();
                case "Q" -> {
                    if (needsToBeSaved) {
                        if (SafeInput.getYNConfirm(in, "There are unsaved changes that exist. Do you want to save your list before quitting? (Y/N)")) {
                            saveList();
                        }
                    }
                    if (SafeInput.getYNConfirm(in, "Are you sure you wish to quit the program? (Y/N): ")) {
                        options = false;
                        System.out.println("Thanks for checking the program out. Have a nice day!");
                    }
                }
            }
        }

    }

    public static void displayOptions() {
        System.out.print ("\nHere is your current list: ");
        if (myArrList.isEmpty()) {
            System.out.println("[Empty]");
        } else {
            for (int i = 0; i < myArrList.size(); i++) {
                System.out.println((i + 1) + ". " + myArrList.get(i));
            }
        }
        System.out.println("\nCommand Options: ");
        System.out.println("A - Add an item to the list");
        System.out.println("D - Delete an item from the list");
        System.out.println("I - Insert an item from the list");
        System.out.println("M - Move an item on the list");
        System.out.println("O - Open a list file from the disk");
        System.out.println("S - Save the current list file to the disk");
        System.out.println("C - Clear the current list");
        System.out.println("V - View the list file");
        System.out.println("Q - Quit the program");

    }

    public static void addItem() {
        String item = SafeInput.getRegExString(in, "Enter an item you wish to add to the list.", ".*");
        myArrList.add(item);
        System.out.println("Your item has been added to the list.");
        needsToBeSaved = true;
    }

    public static void deleteItem() {
        if (myArrList.isEmpty()) {
            System.out.println("Your list is already empty.");
            return;
        }
        int itemNumber = SafeInput.getRangedInt(in, "Enter the number of the item in the list you would like to delete.", 1, myArrList.size());
        myArrList.remove(itemNumber - 1);
        System.out.println("Item #" + itemNumber + " has been removed.");
        needsToBeSaved = true;
    }

    public static void insertItem() {
        String item = SafeInput.getRegExString(in, "Enter an item you wish to add to the list.", ".*");
        int position = SafeInput.getRangedInt(in, "Enter the position in which you would like to add an item", 1, myArrList.size() + 1);
        myArrList.add(position - 1, item);
        System.out.println("Your item has been inserted into position #" + (position));
        needsToBeSaved = true;
    }

    private static void viewList() {
        System.out.println("\nHere is your current list:");
        if (myArrList.isEmpty()) {
            System.out.println("[Empty]");
        } else {
            for (int i = 0; i < myArrList.size(); i++) {
                System.out.println((i + 1) + ". " + myArrList.get(i));
            }
        }
    }

    private static void moveItem() {
        if (myArrList.isEmpty()) {
            System.out.println("The current list is empty. There is nothing to move on the list.");
            return;
        }

        int currentItemNumber = SafeInput.getRangedInt(in, "Please enter the number of the item on the list that you want to move:", 1, myArrList.size());
        int newItemNumber = SafeInput.getRangedInt(in, "Please enter the number of new position for the item on the list you want to move:", 1, myArrList.size());

        String item = myArrList.remove(currentItemNumber - 1);
        myArrList.add(newItemNumber - 1, item);

        needsToBeSaved = true;
        System.out.println("The item you wanted to move has been moved successfully.");
    }

    private static void openList() {
        if (needsToBeSaved) {
            if (!SafeInput.getYNConfirm(in, "There are unsaved changes on your list. Do you want to continue without saving? (Y/N)")) {
                return;
            }
        }

        System.out.print("Enter filename to load (without the extension): ");
        String filename = in.nextLine().trim() + ".txt";

        try (Scanner fileScanner = new Scanner(new File(filename))) {
            myArrList.clear();
            while (fileScanner.hasNextLine()) {
                myArrList.add(fileScanner.nextLine());
            }
            needsToBeSaved = false;
            System.out.println("List loaded successfully from " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("The file was unable to be found: " + filename);
        }
    }

    private static void saveList() {
        if (myArrList.isEmpty()) {
            System.out.println("The current list is empty. There is nothing to save.");
            return;
        }

        System.out.println("Enter the name you would like to save the file as. (Extension not needed)");
        String filename = in.nextLine().trim() + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (String item : myArrList) {
                writer.println(item);
            }
            needsToBeSaved = false;
            System.out.println("List saved successfully as " + filename);
        } catch (IOException error) {
            System.out.println("Error saving the list: " + error.getMessage());
        }

    }

    private static void clearList() {
        if (myArrList.isEmpty()) {
            System.out.println("The current list is empty. There is nothing to be cleared");
            return;
        }
            myArrList.clear();
            needsToBeSaved = true;
            System.out.println("The current list has been cleared. Make sure to save in order for the changes to be kept.");

        }

}