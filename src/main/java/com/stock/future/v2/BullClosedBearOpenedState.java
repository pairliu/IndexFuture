package com.stock.future.v2;

public class BullClosedBearOpenedState extends AbstractState {
    private Main main;

    public BullClosedBearOpenedState(Main main) {
        this.main = main;
    }

    public void execute(double open, double curr, double hi, double lo, double fluc) {
//        if ( curr > ( 1 + fluc ) * manager.getOrderIndex() ) {
        if ( curr > ( 1 + fluc ) * manager.getCurrOrder().getOpenIndex() ) {
//            manager.closeBearOrder( curr );
            manager.closeOrder(curr);
            manager.openBullOrder( curr );
            main.setCurrState( main.getBullOpenedState() );
        }
    }
    
    public String toString() {
        return "BullClosedBearOpenedState";
    }

}
