package com.stock.future.v2.order;

import java.util.Date;

public abstract class Order {
    private double _openIndex;
    private double _closeIndex;
    private Status _orderStatus;
    private Date _openTime;
    private Date _closeTime;
    //In stock, "lot" is the term to represent the unit in one transaction.
    //However, not sure if it is suitable for Index Future. 
    //So use "unit" as the term.
    private int units;

    public static enum Status {
        NONE,
        OPEN,
        CLOSE
    }

    public Order() {}
    
    public Order(final double openIndex) {
        _openIndex = openIndex;
    }
    
    public double getOpenIndex() {
        return _openIndex;
    }
    
    public Order setOpenIndex(final double openIndex) {
        _openIndex = openIndex;
        return this;
    }
    
    public double getCloseIndex() {
        return _closeIndex;
    }
    
    public Order setCloseIndex(final double closeIndex) {
        _closeIndex = closeIndex;
        return this;
    }
    
    public Status getOrderStatus() {
        return _orderStatus;
    }
    
    public Order setOrderStatus(final Status orderStatus) {
        _orderStatus = orderStatus;
        return this;
    }
    
    public Date getOpenTime() {
        return _openTime;
    }

    public Order setOpenTime(final Date openTime) {
        _openTime = openTime;
        return this;
    }

    public Date getCloseTime() {
        return _closeTime;
    }

    public Order setCloseTime(final Date closeTime) {
        _closeTime = closeTime;
        return this;
    }
    
    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }
    
    public abstract double calculateProfit();
}
