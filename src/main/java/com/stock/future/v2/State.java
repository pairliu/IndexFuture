package com.stock.future.v2;

import com.stock.future.v2.order.OrderManager;
import com.stock.future.v2.order.OrderManagerMemoryImpl;

public interface State {
//    public final OrderManager manager = OrderManager.getInstance();
    public final OrderManager manager = new OrderManagerMemoryImpl();
    
    public OrderManager getOrderManager();
    
    public void execute( double open, double curr, double hi, double lo, double fluc );

}
