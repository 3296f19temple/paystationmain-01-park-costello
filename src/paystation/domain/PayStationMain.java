/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;
import java.rmi.server.ExportException;
import java.util.Scanner;

enum Town {
    Alphatown, Betatown, Gammatown
    //accepted values should be A, B, or G for the respective towns
}

/**
 *
 * @author Christopher Park
 * @author Noah Costello
 */


public class PayStationMain {



    //public int printReceipt(){

    //}

    public static void main(String[] args){//the input array args is there to declare initial rate on startup
        //If no initial rate is given, the rate will default to Alphatown, the linear rate
        //System.out.println("Welcome to the paystation.");

        Scanner userInput = new Scanner(System.in);
        String input;
        Town currentLocation;
        boolean admin = false; // changed with login/logout 
        boolean activeTransaction = true; //true until a transaction is close

        //Paystation object with associated rate and town now created
                
        activeTransaction = true; 
        System.out.println("Paystation is now active. Starting new transaction.");

        PayStationImpl ps = new PayStationImpl(Town.Alphatown, new LinearRateStrategy());
        while(activeTransaction){
            System.out.println("What would you like to do?");
            input = userInput.nextLine();
            /* Paystation should be able to accept coins using addPayment(), 
            *  Show time bought on display using readDisplay(),
            *  Print parking tickets using buy(),
            *  Print total returned value and number of each coin type with cancel(),
            *  and Change between rates using a function in this while loop.
            */
            if(input.equals("add payment")){//addPayment
                System.out.println("what coin are you entering? (5, 10, 25). Enter 1 to stop adding coins");
                int coin;
                while(true) {
                    input = userInput.nextLine();
                    try {
                        coin = Integer.parseInt(input);
                    } catch (Exception e) {
                        System.out.println("You must enter a coin value.");
                        continue;
                    }
                    try {
                        ps.addPayment(coin);
                        System.out.println("You have added: " + coin);
                    } catch (IllegalCoinException e) {
                        if(coin == 1) {
                            System.out.println("You have stopped entering coins");
                            break;
                        }
                        System.out.println("You must enter 5, 10, or 25");
                        continue;
                    }
                }

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
                        System.out.println("logged in");
                    }
                    else{
                        System.out.println("login failed");
                    }
                }
                else{
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
                    if(input.equals("Alphatown")){
                        ps.changeTown(Town.Alphatown, new LinearRateStrategy());
                        System.out.println("Town changed to Alphatown");
                    }
                    else if(input.equals("Betatown")){
                        ps.changeTown(Town.Betatown, new ProgressiveRateStrategy());
                        System.out.println("Town changed to Betatown");
                    }
                    else if(input.equals("Gammatown")){
                        ps.changeTown(Town.Gammatown, new AlternatingRateStrategy());
                        System.out.println("Town changed to Gammatown");
                    }
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
                activeTransaction = false;
            }
            else{
                System.out.println("input not recognized. Try help if lost");
            }

        }
    }
    

}
