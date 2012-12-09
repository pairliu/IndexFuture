package com.stock.future.v2.order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;

public class OrderManagerMemoryImpl extends AbstractOrderManager {
    private static Logger LOG = Logger.getLogger( OrderManagerMemoryImpl.class );
    
    private Collection<Order> orders = new ArrayList<Order>();
    
    public void openBullOrder(final double openIndex) {
        Order o = new BullOrder(openIndex);
        o.setOpenTime(new Date());
        o.setOrderStatus(OrderStatus.BULL_OPEN);
        
        setCurrOrder(o);
    }
    public void openBearOrder(final double openIndex) {
        Order o = new BearOrder(openIndex);
        o.setOpenTime(new Date());
        o.setOrderStatus(OrderStatus.BEAR_OPEN);
        
        setCurrOrder(o);
    }
    
    public void closeOrder(final double closeIndex) {
        Order o = getCurrOrder();
        o.setCloseIndex(closeIndex);
        o.setCloseTime(new Date());
        
        orders.add(o);
    }
    
    public boolean isOrderOpened() {
        return false;
    }
}
