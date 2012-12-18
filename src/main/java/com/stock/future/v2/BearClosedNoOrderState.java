package com.stock.future.v2;

public class BearClosedNoOrderState extends AbstractState {
    private Main main;

    public BearClosedNoOrderState(Main main) {
        this.main = main;
    }

    public void execute(double open, double curr, double hi, double lo, double fluc) {
        if (curr < (open * (1- fluc * 2)) && curr < (lo - Main.THRESHOLD)) {
            manager.openBearOrder( curr );
            main.setCurrState( main.getBearOpenedState() );
        } else if (curr > (1 + fluc) * open) {
            manager.openBullOrder( curr );
            main.setCurrState( main.getBullOpenedCleanState() );
        }
    }
    
    public String toString() {
        return "BearClosedNoOrderState";
    }
}
