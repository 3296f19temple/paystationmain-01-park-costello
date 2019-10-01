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

    @Override
    public int calculateTime(int moneyInserted) {
        return 0;
    }

}

class LinearRateStrategy implements RateStrategy {

    @Override
    public int calculateTime(int moneyInserted) {
        return moneyInserted / 5 * 2;
    }

}

