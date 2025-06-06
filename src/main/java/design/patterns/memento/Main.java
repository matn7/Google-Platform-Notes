package design.patterns.memento;

import design.patterns.memento.smartapp.SmartApp;
import design.patterns.memento.smartapp.SmartAppCaretaker;

public class Main {

    public static void main(String[] args) {
        SmartAppCaretaker smartAppCaretaker = new SmartAppCaretaker();
        SmartApp smartApp = new SmartApp();

        smartApp.changeVersion(1.0);
        smartApp.changeVersion(1.1);
        smartApp.changeVersion(1.2);

        smartAppCaretaker.addMemento(smartApp.save());

        smartApp.changeVersion(1.3);
        smartApp.changeVersion(2.0);
        smartApp.changeVersion(2.1);

        smartApp.load(smartAppCaretaker.getMemento(0));
    }
}
