package design.patterns.observer.notification;

import design.patterns.observer.order.Order;

public class TextMessage implements Observer {

    public void update(Order order) {
        System.out.println("SMS - Order number: " + order.getOrderNumber() + " has changed status to: " + order.getOrderStatus());
    }

}
