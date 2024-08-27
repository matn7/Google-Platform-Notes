package com.pandatronik.observer.notification;

import com.pandatronik.observer.order.Order;

public interface Observer {

    void update(Order order);

}
