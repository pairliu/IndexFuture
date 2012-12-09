package com.stock.future.v2.order;

import org.apache.log4j.Logger;

public abstract class AbstractOrderManager implements OrderManagerIntf {
    private static Logger LOG = Logger.getLogger( AbstractOrderManager.class );
    
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
}
