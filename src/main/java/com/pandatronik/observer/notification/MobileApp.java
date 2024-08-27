package com.pandatronik.observer.notification;

import com.pandatronik.observer.order.Order;

public class MobileApp implements Observer {

    public void update(Order order) {
        System.out.println("Mobile App - Order number: " + order.getOrderNumber() + " has changed status to: " + order.getOrderStatus());
    }

}
