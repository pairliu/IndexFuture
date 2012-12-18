package com.stock.future.v2;

public class BullOpenedState extends AbstractState {
    private Main main;

    public BullOpenedState(Main main) {
        this.main = main;
    }

    public void execute(double open, double curr, double hi, double lo, double fluc) {
//        if (curr < ( 1 - fluc/2 ) * manager.getOrderIndex()) {
        if (curr < ( 1 - fluc/2 ) * manager.getCurrOrder().getOpenIndex()) {
//            manager.closeBullOrder( curr );
            manager.closeOrder( curr );
            manager.openBearOrder( curr );
            main.setCurrState( main.getBearOpenedState() );
        }

    }
    
    public String toString() {
        return "BullOpenedState";
    }
}
