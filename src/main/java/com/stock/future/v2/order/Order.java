package com.stock.future.v2.order;

import java.util.Date;

public class Order {
    private double _openIndex;
    private double _closeIndex;
    private OrderStatus _orderStatus;
    private Date _openTime;
    private Date _closeTime;

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
    
    public OrderStatus getOrderStatus() {
        return _orderStatus;
    }
    
    public Order setOrderStatus(final OrderStatus orderStatus) {
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
}
