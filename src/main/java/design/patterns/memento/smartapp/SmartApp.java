package design.patterns.memento.smartapp;

public class SmartApp {

    private Double version;

    public void changeVersion(Double version) {
        this.version = version;
        System.out.println("New version: " + this.version);
    }

    public SmartAppMemento save() {
        return new SmartAppMemento(this.version);
    }

    public void load(SmartAppMemento smartAppMemento) {
        this.version = smartAppMemento.getVersion();
    }

}
