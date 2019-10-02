/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

/**
 *
 * @author Dumpus
 */
public interface RateStrategy {
    
    int calculateTime(int moneyInserted);
    
}

class ProgressiveRateStrategy implements RateStrategy {

    int timeBought = 0;

    @Override
    public int calculateTime(int moneyInserted) {
        int timeBought  = 0;

        if(moneyInserted < 150){
            timeBought = moneyInserted * 2 / 5;
        }
        else if(moneyInserted >= 150 && moneyInserted < 350){
            timeBought = ((moneyInserted - 150)* 3 / 10) + 60;
        }
        else if(moneyInserted >= 350){
            timeBought = ((moneyInserted - 350) / 5) + 120;
        }
        return timeBought;
    }


}

class LinearRateStrategy implements RateStrategy {

    @Override
    public int calculateTime(int moneyInserted) {
        return moneyInserted / 5 * 2;
    }

}

