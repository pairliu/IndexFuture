package com.stock.future.v2.order;

import org.apache.log4j.Logger;

public class BearOrder extends Order {
    private static Logger LOG = Logger.getLogger( BearOrder.class );
    
    public BearOrder() {}
    
    public BearOrder(final double openIndex) {
        super(openIndex);
    }

    @Override
    public double calculateProfit() {
        return getUnits() * (getOpenIndex() - getCloseIndex());
    }

}
