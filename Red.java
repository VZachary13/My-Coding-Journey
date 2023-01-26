import java.lang.*;
import java.util.*;
import java.text.DecimalFormat;

public class Red {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final Integer MONEY = 1000;
    public static final Integer COST = 2; 
    public static final Integer POWERBALL = 1;
    public static final Integer LOTTONUMS = 5;
    public static final Integer TOTALNUMS = 6;
    public static final Integer JACKPOT = 100000000;
    public static final Integer JACKNOT = 5000000;
    public static final Integer FOURPOT = 50000;
    public static final Integer FOURNOT = 100;
    public static final Integer THREEPOT = 100;
    public static final Integer THREENOT = 7;
    public static final Integer TWOPOT = 7;
    public static final Integer ONEPOT = 4;
    public static final Integer POT = 4;
    public static Scanner keyboard = new Scanner(System.in);
    
    public static void main (String[] args) {
        
        int remainingCash = MONEY;
        int[] winners = makeArray();
        int winPowerball = makePowerball();
        sort(winners);


        do {
            displayLetsPlay();
            displayWinConditions();
            displayCash(remainingCash);
            int plays = getNumberOfPlays(getInteger("How many $2 lottery cards do you want to purchase?"), remainingCash);
            remainingCash -= subtractCash(plays);
            displayWinners(winners, winPowerball, plays);
            remainingCash += makeTickets(plays, winners, winPowerball);
        } while (getYorN("\nDo you want to play again? (y/n)") && remainingCash > 0);
        if (remainingCash <= 0) {
            System.out.println("\nYou're all out of money! Better luck next time!");
            System.exit(0);
        }
    }
    public static int makeTickets(int num1, int[] arr2, int num2){
        if (num1 <= 0) {
            return 0;
        }
        int earnings=0;
        System.out.println("Your lottery ticket numbers, followed by the Powerball Number, are:");
        for (int x = num1; x > 0; x--) {
            int[] arr1 = makeArray();
            sort(arr1);
            int tempBall = makePowerball();
            earnings += compareTickets(arr1, tempBall, arr2, num2);
        }
        return earnings;
    }

    public static int compareTickets(int[] arr1, int tempBall, int[] arr2, int num2) {
        Integer winCounter = 0;
        for (int i = 0; i < arr1.length; i++) {
            Integer redCounter = 0;
            for (int j = 0; j < arr2.length; j++) {
                if (arr1[i]==arr2[j]) {
                    System.out.print(ANSI_RED + arr1[i] + ANSI_RESET+" ");
                    winCounter++;
                    redCounter++;
                }
            }
            if (redCounter == 0) {
                if (arr1[i] < 10) {
                    System.out.print(" "+arr1[i]+" "); 
                } else {
                System.out.print(arr1[i]+" ");
                }
            }
        }
        boolean pwrBall;
        if (tempBall == num2) {
            System.out.print(" "+ANSI_RED + tempBall + ANSI_RESET);
            pwrBall = true;
        } else {
            System.out.print(" " + tempBall);
            pwrBall = false;
        }
        Integer earnings = giveMoney(winCounter, pwrBall);
        return earnings;
    }

    public static int giveMoney (int matches, boolean pwrball) {
        switch (matches) {
            case 5:
                return jackPot(pwrball);
            case 4:
                return fourPot(pwrball);
            case 3:
                return threePot(pwrball);
            case 2:
                return twoPot(pwrball);
            case 1:
                return onePot(pwrball);
            case 0:
                return Pot(pwrball);
            default:
                System.out.print("\n");
                return 0;
        }
    }

    public static int jackPot(boolean pwrball){
        
        if (pwrball) {
            System.out.print("  You won $"+JACKPOT+"\n");
            return JACKPOT;
        } else {
            System.out.print("  You won $"+JACKNOT+"\n");
            return JACKNOT;
        }
    }

    public static int fourPot(boolean pwrball){
        
        if (pwrball) {
            System.out.print("  You won $"+FOURPOT+"\n");
            return FOURPOT;
        } else {
            System.out.print("  You won $"+FOURNOT+"\n");
            return FOURNOT;
        }
    }

    public static int threePot(boolean pwrball){
        
        if (pwrball) {
            System.out.print("  You won $"+THREEPOT+"\n");
            return THREEPOT;
        } else {
            System.out.print("  You won $"+THREENOT+"\n");
            return THREENOT;
        }
    }

    public static int twoPot(boolean pwrball){
        
        if (pwrball) {
            System.out.print("  You won $"+TWOPOT+"\n");
            return TWOPOT;
        } else {
            System.out.print("\n");
            return 0;
        }
    }

    public static int onePot(boolean pwrball){
        
        if (pwrball) {
            System.out.print("  You won $"+ONEPOT+"\n");
            return ONEPOT;
        } else {
            System.out.print("\n");
            return 0;
        }
    }

    public static int Pot(boolean pwrball){
        
        if (pwrball) {
            System.out.print("  You won $"+POT+"\n");
            return POT;
        } else {
            System.out.print("\n");
            return 0;
        }

    }

    public static void displayLetsPlay() {
        
        System.out.println("****************************************************");
        System.out.println(ANSI_RED + "Let's play Powerball!" + ANSI_RESET);
        System.out.println("****************************************************");
        System.out.println("");
    }

    public static void displayWinConditions() {
        
        System.out.println("5 numbers correct plus powerball = $100,000,000\n5 numbers correct & no powerball = $5,000,000\n4 numbers correct plus powerball = $50,000\n4 numbers correct & no powerball = $100\n3 numbers correct plus powerball = $100\n3 numbers correct & no powerball = $7\n2 numbers correct plus powerball = $7\n1 number  correct plus powerball = $4\n0 numbers correct plus powerball = $4 ");
        System.out.println("****************************************************");
    }

    public static void displayCash (Integer num1){
        System.out.println("\nYou have $"+num1);
    }

    public static int getNumberOfPlays(Integer num1, Integer num2) {
        if (num1 <= 0) {
            displayCash(num2);
            System.out.println("Goodbye!");
            System.exit(0);
        } else if (num1*COST > num2){
            System.out.println("Insufficient Funds!");
            return 0;
        }  
        return num1; 
    }

    public static int subtractCash(Integer num1) {
        
        return num1*COST;
    }

    public static void displayWinners (int[] arr, int num1, int num3) {
        if (num3 <= 0) {
            return;
        }
        System.out.println("\nThe winning lottery numbers, followed by the Powerball Number, are:");
        for (int x = 0; x < arr.length; ++x) {
            System.out.print(arr[x]+" ");
        }
            System.out.print(" "+ANSI_RED + num1 + ANSI_RESET+"\n");
            System.out.print("\n");
    }

    public static String getString(String msg) {
        String answer = "";
        System.out.println(msg);
        try {
            answer = keyboard.nextLine(); 
        }
        catch (Exception e) {
            System.err.println("Error reading input from user. Ending program.");
            System.exit(-1);
        } 
        
        while (answer.replace(" ", "").equals("")) {
            System.err.println("Error: Missing input.");
            try {
                System.out.println(msg);
                answer = keyboard.nextLine(); 
            }
           catch (Exception e) {
                System.err.println("Error reading input from user. Ending program.");
                System.exit(-1);
           } 
        }
        return answer;            
    }

    public static int getInteger(String msg) {
        System.out.println(msg);
        while (!keyboard.hasNextInt()) {
           keyboard.nextLine();
           System.err.println("Invalid integer. Try again.");
        }
        int number = keyboard.nextInt();
        keyboard.nextLine(); //flushes the buffer
        return number;
    }

    public static double getDouble(String msg) {
        System.out.println(msg);
        while (!keyboard.hasNextDouble()) {
           keyboard.nextLine();
           System.err.println("Invalid number. Try again.");
        }
        double number = keyboard.nextDouble();
        keyboard.nextLine(); //flushes the buffer
        return number;
    }

    public static boolean getYorN(String msg) {
        String answer = getString(msg);
        
        while (answer.compareToIgnoreCase("y")   != 0 
            && answer.compareToIgnoreCase("yes") != 0 
            && answer.compareToIgnoreCase("n")   != 0 
            && answer.compareToIgnoreCase("no")  != 0) {
           
            if (answer.replace(" ", "").equals("")) {
                System.err.println("Error: Missing y/n input.");
            } else {
                if (answer.compareToIgnoreCase("y")   != 0 
                && answer.compareToIgnoreCase("yes") != 0 
                && answer.compareToIgnoreCase("n")   != 0 
                && answer.compareToIgnoreCase("no")  != 0) {
                 System.err.println("Error: Unexpected input.");
                }
            }
           answer = getString(msg);
        } 
        
        if  (answer.compareToIgnoreCase("y")   == 0  
          || answer.compareToIgnoreCase("yes") == 0) {
           return true;
        } 
        else {
           return false;
        }
    } 

    public static int[] makeArray() {
        int [] arr = new int[LOTTONUMS];
        //define ArrayList to hold Integer objects
        ArrayList numbers = new ArrayList();
        for(int i = 0; i < 69; i++) {
        numbers.add(i+1);
        }

        Collections.shuffle(numbers);

        for (int x = 0; x < LOTTONUMS; x++) {

            arr[x] = (int)numbers.get(x);
        }

        return arr;
    }

    public static int makePowerball() {
        int arr = 0;
        //define ArrayList to hold Integer objects
        ArrayList numbers = new ArrayList();
        for(int i = 0; i < 26; i++) {
        numbers.add(i+1);
        }

        Collections.shuffle(numbers);

        for (int x = 0; x < POWERBALL; x++) {

            arr = (int)numbers.get(x);
        }

        return arr;
    }

    public static void sort(int[] arr){
        int n = arr.length;
  
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;
  
            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }


    }
}
