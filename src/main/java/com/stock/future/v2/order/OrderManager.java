package com.stock.future.v2.order;

public interface OrderManager {
    
    //These two methods should be combined to a general one
    public void openBullOrder(final double openIndex);
    
    public void openBearOrder(final double openIndex);
    
    //No need to differentiate bull or bear. Polymorphism will handle it.
//    public void closeBullOrder(final double closeIndex);
    
//    public void closeBearOrder(final double closeIndex);
    public void closeOrder(final double closeIndex);
    
    public boolean isOrderOpened();
    
    public Order getCurrOrder();
    
    public double calculateTotalProfit();

}
