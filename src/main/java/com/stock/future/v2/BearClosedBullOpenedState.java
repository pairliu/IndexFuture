package com.stock.future.v2;

public class BearClosedBullOpenedState extends AbstractState {
    private Main main;

    public BearClosedBullOpenedState(Main main) {
        this.main = main;
    }

    public void execute(double open, double curr, double hi, double lo, double fluc) {
//        if (curr < ( 1 - fluc ) * manager.getOrderIndex()) {
        if (curr < ( 1 - fluc ) * manager.getCurrOrder().getOpenIndex()) {
//            manager.closeBullOrder( curr );
            manager.closeOrder( curr );
            manager.openBearOrder( curr );
            main.setCurrState( main.getBearOpenedState() );
        }
        
    }
    
    public String toString() {
        return "BearClosedBullOpenedState";
    }
}
