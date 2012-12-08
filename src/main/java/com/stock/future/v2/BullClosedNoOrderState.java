package com.stock.future.v2;

public class BullClosedNoOrderState implements State {
    private Main main;
    
    public BullClosedNoOrderState(Main main) {
        this.main = main;
    }

    public void execute(double open, double curr, double hi, double lo, double fluc) {
        if ( curr > (open * (1 + fluc * 2))  && curr > ( hi + Main.THRESHOLD)) {
            manager.openBullOrder( curr );
            main.setCurrState( main.getBullOpenedState() );
        } else if ( curr  < (1 - fluc) * open ) {
            manager.openBearOrder( curr );
            main.setCurrState( main.getBearOpenedCleanState() );
        }
    }
    
    public String toString() {
        return "BullClosedNoOrderState";
    }
}
