import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SafeInput {
    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String retString = " "; // Set this to zero length. Loop runs until it isn't
        do
        {
            System.out.print("\n" +prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        }while(retString.length() == 0);

        return retString;

    }

    public static int getInt(Scanner pipe, String prompt) {

        int retInt = 0;
        boolean correct = false;

        do {
            System.out.print("\n" + prompt + ": "); // show prompt add space
        try {    retInt = pipe.nextInt();
            if (retInt > 0) {
                correct = true;
            } else {
                System.out.println("Invalid Input. Please enter a valid Integer/Number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input. Please enter a valid Integer/Number.");
                pipe.next();
            }
        } while (!correct);

        return retInt;
    }

    public static double getDouble(Scanner pipe, String prompt) {

        double retDouble = 0.0;
        boolean correct = false;

        do {
            System.out.print("\n" + prompt + ": "); // show prompt add space
           try { retDouble = pipe.nextDouble();
               if (retDouble > 0.0) {
                   correct = true;
               } else {
                   System.out.println("Invalid Input. Please enter a valid Double/Number.");
               }
           } catch (InputMismatchException e) {
               System.out.println("Invalid Input. Please enter a valid Double/Number.");
               pipe.next();
           }
        } while (!correct);

        return retDouble;

    }

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {

        int retInt = 0;
        boolean correct = false;

        do {
            System.out.print("\n" + prompt + " (Range: " + low + " - " + high + "): "); // show prompt add space
            try { retInt = pipe.nextInt();
            if  (retInt >= low && retInt <= high) {
                correct = true;
            } else if (retInt < low) {
                System.out.println("You input a number that is lower than the range. Please enter a higher number.");
            } else if (retInt > high) {
                System.out.println("You input a number that is higher than the range. Please enter a lower number.");
                }
            } catch (InputMismatchException e){
                    System.out.println("Invalid Input. Please enter a valid Integer/Number.");
                    pipe.next();
                }
        } while (!correct);

        return retInt;

    }
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {

        double retDouble = 0.0;
        boolean correct = false;

        do {
            System.out.print("\n" + prompt + " (Range: " + low + " - " + high + "): "); // show prompt add space
            try { retDouble = pipe.nextInt();
                if  (retDouble >= low && retDouble <= high) {
                    correct = true;
                } else if (retDouble < low) {
                    System.out.println("You input a number that is lower than the range. Please enter a higher number.");
                } else if (retDouble > high) {
                    System.out.println("You input a number that is higher than the range. Please enter a lower number.");
                }
            } catch (InputMismatchException e){
                System.out.println("Invalid Input. Please enter a valid Integer/Number.");
                pipe.next();
            }
        } while (!correct);

        return retDouble;

    }

    public static boolean getYNConfirm(Scanner pipe, String prompt) {

        boolean getYNConfirm = false;
        boolean valid = false;
        String response = "";

        do {
            System.out.print("\n" + prompt + ": "); // show prompt add space
            response = pipe.nextLine();

            if (response.equalsIgnoreCase("Y")) {
                getYNConfirm = true;
                valid = true;
            } else if (response.equalsIgnoreCase("N")) {
                getYNConfirm = false;
                valid = true;
            } else {
                System.out.println("Please respond with Y (Yes) or N (No)");
            }

        } while (!valid);

        return getYNConfirm;

    }

    public static String getRegExString(Scanner pipe, String prompt, String regEx) {

        String getRegExString = "";
        boolean correct = false;

        do {
            System.out.print("\n" + prompt + ": "); // show prompt add space
            getRegExString = pipe.nextLine();
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(getRegExString);

            if (matcher.matches()) {
                correct = true;
            } else {
                System.out.println("Invalid input. Please respond with a string with the correct RegEx");
            }

        } while (!correct);

        return getRegExString;

    }
    public static void prettyHeader(String msg) {

        int width = 60;
        int msgLength = msg.length();
        int padding = (width - msgLength - 6) / 2;
        boolean correct = false;

        do {
            if (msgLength < 54) {
                for (int i = 0; i < width; i++) {
                    System.out.print("*");
                }
                System.out.println();

                System.out.print("***");
                for (int i = 0; i < padding; i++) {
                    System.out.print(" ");
                }
                System.out.print(msg);
                for (int i = 0; i < padding; i++) {
                    System.out.print(" ");
                }
                System.out.println("***");

                for (int i = 0; i < width; i++) {
                    System.out.print("*");
                }
                System.out.println();
            correct = true;
            } else {
                System.out.println("Please input a message that is less than 55 characters long.");
                Scanner in = new Scanner(System.in);
                System.out.print("Please enter your message: ");
                msg = in.nextLine();
                msgLength = msg.length();
                padding = (width - msgLength - 6) / 2;
            }
        } while (!correct);
    }
    public static double CtoF ( double celsius) {

        return (celsius * 9 / 5) + 32;
    }
}















