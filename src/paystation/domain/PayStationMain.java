/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;
import java.util.Scanner;

/**
 *
 * @author Christopher Park
 * @author Noah Costello
 */
public class PayStationMain {
    public static void main(String args[]){//the input array args is there to declare initial rate on startup
        //If no initial rate is given, the rate will default to Alphatown, the linear rate
        //System.out.println("Welcome to the paystation.");
        
        boolean activeTransaction; //true until a transaction is closed
        char location; //accepted values should be A, B, or G for the respective towns
        
        if(args.length > 0){ //initial rate setup if cmd line input given
            switch(args[0].charAt(0)){
                case 'A':
                    location = 'A';
                    System.out.println("Booting up paystation with Alphatown and linear rate.");
                case 'B':
                    location = 'B';
                    System.out.println("Booting up paystation with Betatown and progressive rate.");
                case 'G':
                    location = 'G';
                    System.out.println("Booting up paystation with Gammatown and alternating rate.");
                default://if detected input is not a valid input
                    System.out.println("detected input is not valid. Defaulting to Alphatown and linear rate.");
                    location = 'A';
            }
        }
        else{//initial rate setup if cmd line input not given. Defaults to linear rate
            location = 'A';
            System.out.println("Booting up paystation with Alphatown and linear rate.");
        }//initial rate char now set up
                
        activeTransaction = true; 
        System.out.println("Paystation is now active. Starting new transaction.");

        while(activeTransaction){
            /* Paystation should be able to accept coins using addPayment(), 
            *  Show time bought on display using readDisplay(),
            *  Print parking tickets using buy(),
            *  Print total returned value and number of each coin type with cancel(),
            *  and Change between rates using a function in this while loop.
            */
            activeTransaction = false; //temp function to close loop. Comment out later.
        }
    }   
}
