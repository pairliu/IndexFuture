package com.stock.future.v2;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.stock.future.FileIndexRetriever;
import com.stock.future.IndexRetriever;

public class Main {
    private static Logger LOG = Logger.getLogger( Main.class );
    
    public static final int THRESHOLD = 3;
    
    private static double openIndex;
    private static double currentIndex;
    private static double highIndex;
    private static double lowIndex;
    private static double lastIndex; //for closeOrderAnyway at the end of the program
    
    //The important parameter (0.2% for test)
    private static double fluctuate = 0.003;
    
    //The current state
    private State currState;
    
    //All states
    private Map<String, State> states = new HashMap<String, State>();
    
    public Main() {
        initAllStates();
        
        setCurrState( getInitState() );
    }

    public State getCurrState() {
        return currState;
    }

    public void setCurrState(State state) {
        this.currState = state;
    }
    
    public void run() throws Exception {
        IndexRetriever r = new FileIndexRetriever();
        
        //Initialize the values
        highIndex = lowIndex = openIndex = r.getCurrentIndex();
        
        while( true ) {
            currentIndex = r.getCurrentIndex();
            
            //To terminate the loop. Just for test.
            if ( currentIndex < 1 ) {
                if (currState.getOrderManager().isOrderOpened()) {
                    currState.getOrderManager().closeOrder(lastIndex);
                    LOG.info("Close order anyway at index: " + lastIndex);
                }
                
                double total = currState.getOrderManager().calculateTotalProfit();
                LOG.info("Total profit is: " + total);
                break;
            }
            
//            System.out.println( "Current Index is: " + currentIndex );
//            System.out.println( "Current State is: " + currState);
            
            currState.execute(openIndex, currentIndex, highIndex, lowIndex, fluctuate);
            
            //The highIndex and lowIndex should be updated at the end of one loop, 
            //or there will some conditions invalid
            updateHighLowIndex( currentIndex );
            lastIndex = currentIndex;
            
//            TimeUnit.MILLISECONDS.sleep(100);
        }
    }
    
    public State getInitState() {
        return states.get( "init" );
    }
    
    public State getBullOpenedCleanState() {
        return states.get( "bullOpenedClean" );
    }
    
    public State getBearOpenedCleanState() {
        return states.get( "bearOpenedClean" );
    }
    
    public State getBullClosedBearOpenedState() {
        return states.get( "bullClosedBearOpened" );
    }
    
    public State getBullClosedNoOrderState() {
        return states.get( "bullClosedNoOrder" );
    }
    
    public State getBearClosedBullOpenedState() {
        return states.get( "bearClosedBullOpened" );
    }
    
    public State getBearClosedNoOrderState() {
        return states.get( "bearClosedNoOrder" );
    }
    
    public State getBullOpenedState() {
        return states.get( "bullOpened" );
    }
    
    public State getBearOpenedState() {
        return states.get( "bearOpened" );
    }
    
    private void initAllStates() {
        State s = new InitState(this);
        states.put( "init", s );
        s = new BullOpenedCleanState(this);
        states.put( "bullOpenedClean", s );
        s = new BearOpenedCleanState(this);
        states.put( "bearOpenedClean", s );
        s = new BullClosedBearOpenedState(this);
        states.put( "bullClosedBearOpened", s );
        s = new BullClosedNoOrderState(this);
        states.put( "bullClosedNoOrder", s );
        s = new BearClosedBullOpenedState(this);
        states.put( "bearClosedBullOpened", s );
        s = new BearClosedNoOrderState(this);
        states.put( "bearClosedNoOrder", s );
        s = new BullOpenedState(this);
        states.put( "bullOpened", s );
        s = new BearOpenedState(this);
        states.put( "bearOpened", s );
    }
    
    private void updateHighLowIndex( double index ) {
        if ( index > highIndex ) highIndex = index;
        if ( index < lowIndex ) lowIndex = index;
    }
    
    public static void main(String[] args) throws Exception {
        new Main().run();
    }
}
