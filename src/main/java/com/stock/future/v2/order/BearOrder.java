package com.stock.future.v2.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BearOrder extends Order {
    private static Logger LOG = LoggerFactory.getLogger( BearOrder.class );
    
    public BearOrder() {}
    
    public BearOrder(final double openIndex) {
        super(openIndex);
    }

    @Override
    public double calculateProfit() {
        return getUnits() * (getOpenIndex() - getCloseIndex());
    }

}
