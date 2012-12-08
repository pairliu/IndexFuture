package com.stock.future.v2.order;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;


//At any time there is only one order!
public class OrderManager {
    private static Logger LOG = Logger.getLogger( OrderManager.class );
    
    private Order _currOrder;
    
    public Order getCurrOrder() {
        return _currOrder;
    }
    public void setCurrOrder(final Order currOrder) {
        _currOrder = currOrder;
    }

    private OrderStatus orderStatus = OrderStatus.NONE;
    
    private double orderIndex;    
    public double getOrderIndex() {
        return orderIndex;
    }

    private static OrderManager instance = new OrderManager();
    private OrderManager() {}
    public static OrderManager getInstance() { return instance; }
    
    private SimpleDateFormat df = new SimpleDateFormat( "yyyy.MM.dd 'at' HH:mm:ss" );
    
    public void openBullOrder( double index ) {
        validateOpenStatus();
        
        orderIndex = index;
        orderStatus = OrderStatus.BULL_OPEN;
        //Ideally the order information should be saved to some persistent place.
        //MongoDB should be a good place.
        LOG.info( "BULL order opened at index " + orderIndex + "! Time is:" + getFormattedCurrentTime() );
    }
    
    public void openBearOrder( double index ) {
        validateOpenStatus();
        
        orderIndex = index;
        orderStatus = OrderStatus.BEAR_OPEN;
        
        LOG.info( "BEAR order opened at index " + orderIndex + "! Time is:" + getFormattedCurrentTime() );
    }
    
    public void closeBullOrder( double index ) {
        validateCloseBullStatus();
        
        orderIndex = index;
        orderStatus = OrderStatus.BULL_OPENED_BUT_CLOSE;
        
        LOG.info( "BULL order closed at index " + orderIndex + "! Time is:" + getFormattedCurrentTime() );
    }
    
    public void closeBearOrder( double index ) {
        validateCloseBearStatus();
        
        orderIndex = index;
        orderStatus = OrderStatus.BEAR_OPENED_BUT_CLOSE;
        
        LOG.info( "BEAR order closed at index " + orderIndex + "! Time is:" + getFormattedCurrentTime() );
    }
    
    public void closeOrderAnyway(double index) {        
        if ( bullOrderOpened() ) closeBullOrder( index );
        
        if ( bearOrderOpened() ) closeBearOrder( index );
    }

    
    public boolean bullOrderOpened() {
        return orderStatus == OrderStatus.BULL_OPEN;
    }
    
    public boolean bearOrderOpened() {
        return orderStatus == OrderStatus.BEAR_OPEN;
    }
    
    public boolean noOrderOpenedYet() {
        return orderStatus == OrderStatus.NONE;
    }
    
    public boolean bullOrderJustClosed() {
        return orderStatus == OrderStatus.BULL_OPENED_BUT_CLOSE;
    }
    
    public boolean bearOrderJustClosed() {
        return orderStatus == OrderStatus.BEAR_OPENED_BUT_CLOSE;
    }

    private void validateOpenStatus() {
        if ( orderStatus == OrderStatus.BULL_OPEN || orderStatus == OrderStatus.BEAR_OPEN ) {
            throw new IllegalStateException( "Can't open order. Currently there is already an order with status " + orderStatus );
        }
    }
    
    private void validateCloseBullStatus() {
        if ( orderStatus != OrderStatus.BULL_OPEN ) {
            throw new IllegalStateException( "Can't close bull order. Currently the order status is " + orderStatus );
        }
    }
    
    private void validateCloseBearStatus() {
        if ( orderStatus != OrderStatus.BEAR_OPEN ) {
            throw new IllegalStateException( "Can't close bear order. Currently the order status is " + orderStatus );
        }
    }
    
    private String getFormattedCurrentTime() {
        Date now = new Date();
        return df.format( now );
    }

}
