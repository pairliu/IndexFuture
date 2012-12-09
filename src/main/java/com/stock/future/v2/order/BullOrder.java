package com.stock.future.v2.order;

import org.apache.log4j.Logger;

public class BullOrder extends Order {
    private static Logger LOG = Logger.getLogger( BullOrder.class );
    
    public BullOrder() {}
    
    public BullOrder(final double openIndex) {
        super(openIndex);
    }

}
