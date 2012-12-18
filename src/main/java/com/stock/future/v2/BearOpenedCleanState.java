package com.stock.future.v2;

public class BearOpenedCleanState extends AbstractState {
    private Main main;

    public BearOpenedCleanState(Main main) {
        this.main = main;
    }

    public void execute(double open, double curr, double hi, double lo, double fluc) {
        if ( curr > ( 1 + fluc ) * lo ) {
//            manager.closeBearOrder( curr );
            manager.closeOrder( curr );
            
            if ( lo < open * (1 - 2 * fluc ) ) {
                manager.openBullOrder( curr );
                main.setCurrState( main.getBearClosedBullOpenedState() );
                
            } else {
                main.setCurrState( main.getBearClosedNoOrderState() );
            }
        }

    }
    
    public String toString() {
        return "BearOpenedCleanState";
    }

}
