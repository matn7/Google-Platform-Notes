package design.patterns.templatemethod;

public class ClassicCarStartingSequence extends CarStartingSequence {

    public void setTheGear() {
        System.out.println("Set the gear");
    }

    public void startTheIgnition() {
        System.out.println("Start the ignition");
    }

}
