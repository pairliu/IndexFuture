package com.stock.future.v1;

import java.util.Calendar;

import org.apache.log4j.Logger;

import com.stock.future.FileIndexRetriever;
import com.stock.future.IndexRetriever;

//Make it as a service? Start to run at 9:15? No need. Just run it every day.
//Basically it is just need to know the last status and the current condition
public class Main {
    private static Logger LOG = Logger.getLogger( Main.class );
    
    private static double openIndex;
    private static double currentIndex;
    private static double highIndex;
    private static double lowIndex;
    
    private static final int THRESHOLD = 3;
    
    //The important parameter (0.2% for test)
    private static double fluctuate = 0.002;
    
    public static void main( String[] args ) throws Exception {
        OrderManager manager = OrderManager.getInstance();
        Calendar c1510 = getCalendarOf1510();        
        
        IndexRetriever r = new FileIndexRetriever();
        
        //Initialize the values
        highIndex = lowIndex = openIndex = r.getCurrentIndex();        
        
        //An infinite loop
        while ( true ) {
            currentIndex = r.getCurrentIndex();
            
            //To terminate the loop. Just for test.
            if ( currentIndex < 1 ) {
                break;
            }
            
//            if ( Calendar.getInstance().after(c1510) ) {
//                manager.closeOrderAnyway( currentIndex );
//                LOG.info( "The market will close soon. Any order should be closed."  );
//                //break out of the loop and the program ends
//                break;
//            }

            if ( manager.noOrderOpenedYet() && (currentIndex > (1 + fluctuate) * openIndex) ) {
                manager.openBullOrder( currentIndex );
                
                printStats();
            } 
            
            if ( manager.noOrderOpenedYet() && (currentIndex  < (1 - fluctuate ) * openIndex) ) {
                manager.openBearOrder( currentIndex );
                
                printStats();
            }
            
            //This condition is not good
            if ( manager.bullOrderOpened() && (currentIndex < ( 1 - fluctuate ) * highIndex) ) {
                manager.closeBullOrder( currentIndex );
                printStats();
                //Check if highIndex is too high, and if so, open a bear order.
                //Here the condition doesn't have currentIndex involved.
                if ( highIndex > openIndex * (1 + 2 * fluctuate) ) {
                    manager.openBearOrder( currentIndex );
                    printStats();
                }
            }
            
            //This two conditions are easily matched at the same time. That is, a bear order opened and then it is closed immediately
            
            if ( manager.bearOrderOpened() && (currentIndex > ( 1 + fluctuate ) * lowIndex ) ) {
                manager.closeBearOrder( currentIndex );
                printStats();
                if ( lowIndex < openIndex * (1 - 2 * fluctuate) ) {
                    manager.openBullOrder( currentIndex );
                    printStats();
                }
            }
            
            //This should also add a condition that a bull order has just been closed. 
            //Also the number "3" should be configurable.
            if ( manager.bullOrderJustClosed() && (currentIndex > (openIndex * (1 + fluctuate * 2))  && currentIndex > (highIndex + THRESHOLD) ) ) {
                manager.openBullOrder( currentIndex );
                printStats();
            }
            
            if ( manager.bearOrderJustClosed() && (currentIndex < (openIndex * (1- fluctuate * 2)) && currentIndex < (lowIndex - THRESHOLD) ) ) {
                manager.openBearOrder( currentIndex );
                printStats();
            }
            
            //The highIndex and lowIndex should be updated at the end of one loop, 
            //or there will some conditions invalid
            updateHighLowIndex( currentIndex );
            
            //loop every 1 seconds
//            try {
//                TimeUnit.SECONDS.sleep( 1 );
//            } catch (InterruptedException e) {
//                //What's the general principal to handle InterruptedException?
//                e.printStackTrace();
//            }
        }
    }

    private static Calendar getCalendarOf1510() {
        Calendar c1510 = Calendar.getInstance();
        c1510.set( Calendar.HOUR_OF_DAY , 15 );
        c1510.set( Calendar.MINUTE, 10 );
        return c1510;
    }

    private static void updateHighLowIndex( double index ) {
        if ( index > highIndex ) highIndex = index;
        if ( index < lowIndex ) lowIndex = index;
    }
    
    private static void printStats() {
        LOG.info( "    OpenIndex: " + openIndex );
        LOG.info( "    HighIndex: " + highIndex );
        LOG.info( "    LowIndex:  " + lowIndex );
        LOG.info( "    CurrIndex: " + currentIndex );
    }

}
