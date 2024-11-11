package design.patterns.templatemethod;

public abstract class CarStartingSequence {

    public void startTheCar() {
        fastenSeatbelts();
        startTheIgnition();
        setTheGear();
        go();
    }

    private void go() {
        System.out.println("Go");
    }

    private void fastenSeatbelts() {
        System.out.println("Fasten seat belts");
    }

    public abstract void startTheIgnition();
    public abstract void setTheGear();
}
