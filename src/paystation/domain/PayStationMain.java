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

public class PayStationMain {

    public static void main(String[] args){//the input array args is there to declare initial rate on startup
        //If no initial rate is given, the rate will default to Alphatown, the linear rate
        //System.out.println("Welcome to the paystation.");

        Town currentLocation;
        boolean activeTransaction = true; //true until a transaction is close
        //initial rate setup if cmd line input given

        //Paystation object with associated rate and town now created
                
        activeTransaction = true; 
        System.out.println("Paystation is now active. Starting new transaction.");

        PayStationImpl ps = new PayStationImpl();
        while(activeTransaction){
            ps.runPayStation();

        }
    }
    

}
