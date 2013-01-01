package com.stock.future.v2.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BullOrder extends Order {
    private static Logger LOG = LoggerFactory.getLogger( BullOrder.class );
    
    public BullOrder() {}
    
    public BullOrder(final double openIndex) {
        super(openIndex);
    }
    
    @Override
    public double calculateProfit() {
        return getUnits() * (getCloseIndex() - getOpenIndex());
    }

}
