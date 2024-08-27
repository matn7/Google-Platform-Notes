package com.pandatronik.observer.order;

import com.pandatronik.observer.notification.Observer;

public interface Observable {

    void registerObserver(Observer observer);
    void unregisterObserver(Observer observer);
    void notifyObservers();

}
