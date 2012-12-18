package com.stock.future.v2;

import com.stock.future.v2.order.OrderManager;

public abstract class AbstractState implements State {
    
    public OrderManager getOrderManager() {
        return manager;
    }
}
