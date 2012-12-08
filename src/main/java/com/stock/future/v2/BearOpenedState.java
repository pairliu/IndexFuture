package com.stock.future.v2;

public class BearOpenedState implements State {
    private Main main;

    public BearOpenedState(Main main) {
        this.main = main;
    }

    public void execute(double open, double curr, double hi, double lo, double fluc) {
        if (curr > ( 1 + fluc/2 ) * manager.getOrderIndex()) {
            manager.closeBearOrder( curr );
            manager.openBullOrder( curr );
            main.setCurrState( main.getBullOpenedState() );
        }
    }
    
    public String toString() {
        return "BearOpenedState";
    }
}
