package com.stock.future.v2;

public class InitState extends AbstractState {
    private Main main;
    
    public InitState( Main main ) {
        this.main = main;
    }

    public void execute( double open, double curr, double hi, double lo, double fluc ) {
        
        if ( curr > (1 + fluc) * open ) {
            manager.openBullOrder( curr );
            main.setCurrState( main.getBullOpenedCleanState() );
            
        } else if ( curr  < (1 - fluc ) * open ) {
            manager.openBearOrder( curr );
            main.setCurrState( main.getBearOpenedCleanState() );            
        }
        
    }
    
    public String toString() {
        return "InitState";
    }

}
