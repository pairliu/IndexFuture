package com.stock.future.v2;


public class BullOpenedCleanState implements State {
    private Main main;
    
    public BullOpenedCleanState( Main main ) {
        this.main = main;
    }

    public void execute( double open, double curr, double hi, double lo, double fluc ) {
        if ( curr < ( 1 - fluc ) * hi ) {
            manager.closeBullOrder( curr );
            
            if (hi > open * (1 + 2 * fluc) ) {
                manager.openBearOrder( curr );
                main.setCurrState( main.getBullClosedBearOpenedState() );
            } else {
                main.setCurrState( main.getBullClosedNoOrderState() );
            }
            
        }
    }
    
    public String toString() {
        return "BullOpenedCleanState";
    }

}
