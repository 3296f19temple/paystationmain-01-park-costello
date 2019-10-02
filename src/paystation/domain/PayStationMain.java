/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;
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
class townPaystation{
    Town activeLocation;
    RateStrategy currentRate;

    townPaystation(Town currentLocation){
        this.activeLocation = currentLocation;
    }

    public void changeTown(Town currentLocation){
        this.activeLocation = currentLocation;
    }

    public void updateRate(){
        if(this.activeLocation == Town.Alphatown){
            this.currentRate = LinearRateStrategy;
        }
        else if(this.activeLocation == Town.Betatown){
            this.currentRate = ProgressiveRateStrategy;
        }
        else if(this.activeLocation == Town.Gammatown){
            this.currentRate = AlternatingRateStrategy;
        }
    }
}

public class PayStationMain {



    //public int printReceipt(){

    //}

    public static void main(String[] args){//the input array args is there to declare initial rate on startup
        //If no initial rate is given, the rate will default to Alphatown, the linear rate
        //System.out.println("Welcome to the paystation.");

        Scanner userInput = new Scanner(System.in);
        String input;
        Town currentLocation;
        boolean activeTransaction = true; //true until a transaction is close
        //initial rate setup if cmd line input given

        if(args.length > 0) switch (args[0].charAt(0)) {
            case 'A':
                currentLocation = Town.Alphatown;
                System.out.println("Booting up paystation with Alphatown and linear rate.");
            case 'B':
                currentLocation = Town.Betatown;
                System.out.println("Booting up paystation with Betatown and progressive rate.");
            case 'G':
                currentLocation = Town.Gammatown;
                System.out.println("Booting up paystation with Gammatown and alternating rate.");
            default://if detected input is not a valid input
                System.out.println("detected input is not valid. Defaulting to Alphatown and linear rate.");
                currentLocation = Town.Alphatown;
        } else {//initial rate setup if cmd line input not given. Defaults to linear rate
            currentLocation = Town.Alphatown;
            System.out.println("Booting up paystation with Alphatown and linear rate.");
        }

        townPaystation mobilePay = new townPaystation(currentLocation);
        mobilePay.updateRate();
        //Paystation object with associated rate and town now created
                
        activeTransaction = true; 
        System.out.println("Paystation is now active. Starting new transaction.");

        //PayStation ps = new PayStationImpl();
        while(activeTransaction){
            System.out.println("What would you like to do?");
            input = userInput.nextLine();
            /* Paystation should be able to accept coins using addPayment(), 
            *  Show time bought on display using readDisplay(),
            *  Print parking tickets using buy(),
            *  Print total returned value and number of each coin type with cancel(),
            *  and Change between rates using a function in this while loop.
            */
            userInput.close();
            activeTransaction = false; //temp function to close loop. Comment out later.
        }
    }
    

}
