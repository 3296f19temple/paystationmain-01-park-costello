package paystation.domain;
import java.util.*;

/**
 * Implementation of the pay station.
 *
 * Responsibilities:
 *
 * 1) Accept payment; 
 * 2) Calculate parking time based on payment; 
 * 3) Know earning, parking time bought; 
 * 4) Issue receipts; 
 * 5) Handle buy and cancel events.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class PayStationImpl implements PayStation {

//    enum Town {
//        Alphatown, Betatown, Gammatown
//        //accepted values should be A, B, or G for the respective towns
//    }
    private int insertedSoFar;
    private int timeBought;
    private Map coinMap = new HashMap();
    private boolean nickleBool = false;
    private boolean dimeBool = false;
    private boolean quarterBool = false;
    private Town currentLocation = Town.Alphatown;
    private RateStrategy rateStrategy = new LinearRateStrategy();//Defaults to Alphatown
    private Scanner userInput = new Scanner(System.in);
    private boolean admin = false; // changed with login/logout
    private String input;

    public void setRateStrategy(RateStrategy rs) {
        rateStrategy = rs;
    }

    public void getNewTown(String input) {
        while(true) {
            switch (input) {
                case "Alphatown":
                    currentLocation = Town.Alphatown;
                    setRateStrategy(new LinearRateStrategy());
                    return;
                case "Betatown":
                    currentLocation = Town.Betatown;
                    setRateStrategy(new ProgressiveRateStrategy());
                    return;
                case "Gammatown":
                    currentLocation = Town.Gammatown;
                    setRateStrategy(new AlternatingRateStrategy());
                    return;
                default:
                    System.out.println("That is not an accepted town. Please try again.");
                    break;
            }
        }
    }

    public void runPayStation() {
        System.out.println("Your current location is: " + currentLocation.toString());
        System.out.println("What would you like to do?");
        input = userInput.nextLine();


        /* Paystation should be able to accept coins using addPayment(),
         *  Show time bought on display using readDisplay(),
         *  Print parking tickets using buy(),
         *  Print total returned value and number of each coin type with cancel(),
         *  and Change between rates using a function in this while loop.
         */
        if(input.equals("add payment")){//addPayment

        }
        else if(input.equals("read display")){//readDisplay

        }
        else if(input.equals("buy")){//buy

        }
        else if(input.equals("cancel")){//cancel

        }
        else if(input.equals("login")){//login
            System.out.println("Username: "); //root
            input = userInput.nextLine();
            if(input.equals("root")){
                System.out.println("Password: ");
                input = userInput.nextLine();
                if(input.equals("toor")){
                    admin = true;
                }
                else{
                    System.out.println("login failed");
                }
            }
            else {
                System.out.println("login failed");
            }
        }
        else if(input.equals("logout")){//logout
            admin = false;
        }
        else if(input.equals("change town")){//changeTown and updateRate
            if(admin){
                System.out.println("Enter the new town");
                input = userInput.nextLine();
                getNewTown(input);
            }
            else{
                System.out.println("admin only functionality");
            }
        }
        else if(input.equals("help")){
            System.out.println("Acceptable inputs:");
            System.out.println("add payment");
            System.out.println("read display");
            System.out.println("buy");
            System.out.println("cancel");
            System.out.println("login");
            System.out.println("logout");
            System.out.println("change town");
            System.out.println("shutdown");
        }
        else if(input.equals("shutdown")){
            userInput.close();
        }
        else{
            System.out.println("input not recognized. Try help if lost");
        }
    }
    
    @Override
    public void addPayment(int coinValue)
            throws IllegalCoinException {

        switch (coinValue) {
            case 5:
                if(!nickleBool)
                {
                    coinMap.put(1, 1);
                    nickleBool = true;
                }
                else
                {
                    coinMap.put(1, (int)coinMap.get(1) + 1);
                    
                }
                break;
            case 10:
                if(!dimeBool)
                {
                    coinMap.put(2, 1);
                    dimeBool = true;
                }
                else
                {
                    coinMap.put(2, (int)coinMap.get(2) + 1);
                }
                break;
            case 25:
                if(!quarterBool)
                {
                    coinMap.put(3, 1);
                    quarterBool = true;
                }
                else
                {
                    coinMap.put(3, (int)coinMap.get(3) + 1);
                }
                break;
            default:
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }
        insertedSoFar += coinValue;
        //timeBought = insertedSoFar / 5 * 2; deprecated
        timeBought = rateStrategy.calculateTime(insertedSoFar);
    }

    @Override
    public int readDisplay() {
        return timeBought;
    }

    @Override
    public Receipt buy() {
        Receipt r = new ReceiptImpl(timeBought);
        reset();
        return r;
    }

    @Override
    public Map<Integer, Integer> cancel()
    {
        Map tempMap = new HashMap(coinMap);
        reset();
        return tempMap;
    }
    
    private void reset() {
        timeBought = insertedSoFar = 0;
        nickleBool = false;
        dimeBool = false;
        quarterBool = false;
        coinMap.clear();
    }
    
    @Override
    public int empty()
    {
        int total = insertedSoFar;
        insertedSoFar = 0;
        return total;
    }
}
