package design.patterns.templatemethod;

public class ButtonCarStartingSequence extends CarStartingSequence {

    public void setTheGear() {
        System.out.println("Set the gear");
    }

    public void startTheIgnition() {
        System.out.println("Press Button");
    }

}
