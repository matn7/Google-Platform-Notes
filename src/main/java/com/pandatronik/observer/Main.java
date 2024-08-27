package com.pandatronik.observer;

import com.pandatronik.observer.notification.Email;
import com.pandatronik.observer.notification.MobileApp;
import com.pandatronik.observer.notification.TextMessage;
import com.pandatronik.observer.order.Order;
import com.pandatronik.observer.order.OrderStatus;

public class Main {

    public static void main(String[] args) {
        Order order = new Order(110L, OrderStatus.REGISTERED);

        TextMessage textMessage = new TextMessage();
        MobileApp mobileApp = new MobileApp();
        Email email = new Email();

        order.registerObserver(textMessage);
        order.registerObserver(mobileApp);
        order.registerObserver(email);

        order.notifyObservers();
        System.out.println();

        order.changeOrderStatus(OrderStatus.SENT);

        order.unregisterObserver(email);
        System.out.println();
        order.changeOrderStatus(OrderStatus.RECEIVED);

    }

}
