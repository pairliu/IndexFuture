package com.stock.future.v2;

import com.stock.future.v2.order.OrderManager;

public interface State {
    public final OrderManager manager = OrderManager.getInstance();
    
    public void execute( double open, double curr, double hi, double lo, double fluc );

}
