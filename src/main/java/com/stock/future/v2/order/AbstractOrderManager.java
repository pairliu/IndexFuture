package com.stock.future.v2.order;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.stock.future.v2.order.Order.Status;

public abstract class AbstractOrderManager implements OrderManager {
    private static Logger LOG = Logger.getLogger( AbstractOrderManager.class );
    
    private SimpleDateFormat df = new SimpleDateFormat( "yyyy.MM.dd 'at' HH:mm:ss" );
    
    private Order _currOrder;
    
    public Order getCurrOrder() {
        return _currOrder;
    }
    
    public void setCurrOrder(final Order currOrder) {
        _currOrder = currOrder;
    }
    
    public void closeOrderAnyway(double index) {        
        if ( isOrderOpened() ) closeOrder( index );
    }
    
    public boolean isOrderOpened() {
        return (_currOrder != null && _currOrder.getOrderStatus() == Status.OPEN);
    }
    
    protected String getFormattedCurrentTime() {
        Date now = new Date();
        return df.format( now );
    }
}
