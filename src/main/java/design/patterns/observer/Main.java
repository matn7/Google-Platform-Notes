package design.patterns.observer;

import design.patterns.observer.notification.Email;
import design.patterns.observer.notification.MobileApp;
import design.patterns.observer.notification.TextMessage;
import design.patterns.observer.order.Order;
import design.patterns.observer.order.OrderStatus;

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
