package com.stock.future.v2.order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stock.future.v2.order.Order.Status;

public class OrderManagerMemoryImpl extends AbstractOrderManager {
    private static Logger LOG = LoggerFactory.getLogger( OrderManagerMemoryImpl.class );
    
    private Collection<Order> orders = new ArrayList<Order>();
    
    public void openBullOrder(final double openIndex) {
        Order o = new BullOrder(openIndex);
        o.setOpenTime(new Date());
//        o.setOrderStatus(OrderStatus.BULL_OPEN);
        o.setOrderStatus(Status.OPEN);
        o.setUnits(1); //Currently just set to 1.
        
        setCurrOrder(o);
        
        LOG.info( "BULL order opened at index " + openIndex + "! Time is:" + getFormattedCurrentTime() );
    }
    public void openBearOrder(final double openIndex) {
        Order o = new BearOrder(openIndex);
        o.setOpenTime(new Date());
//        o.setOrderStatus(OrderStatus.BEAR_OPEN);
        o.setOrderStatus(Status.OPEN);
        o.setUnits(1);
        
        setCurrOrder(o);
        
        LOG.info( "BEAR order opened at index " + openIndex + "! Time is:" + getFormattedCurrentTime() );
    }
    
    public void closeOrder(final double closeIndex) {
        Order o = getCurrOrder();
        o.setCloseIndex(closeIndex);
        o.setOrderStatus(Status.CLOSE);
        o.setCloseTime(new Date());
        
        orders.add(o);
        
        setCurrOrder(null);
        
        LOG.info( "     Order closed at index " + closeIndex + "! Time is:" + getFormattedCurrentTime() );
    }
    
    public double calculateTotalProfit() {
        double result = 0.0;
        for (Order order : orders) {
            result += order.calculateProfit();
        }
        
        return result;
    }
}
