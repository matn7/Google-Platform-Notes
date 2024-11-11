package design.patterns.chainofresponsibility;

import design.patterns.chainofresponsibility.message.Message;
import design.patterns.chainofresponsibility.officer.*;

public class Main {

    public static void main(String[] args) {
        Message message = new Message("Accack", 20, OfficerRank.GENERAL);

        Officer sergeant = new Sergeant();
        Officer captain = new Captain();
        Officer general = new General();

        sergeant.setSuperiorOfficer(captain);
        captain.setSuperiorOfficer(general);

        sergeant.processMessage(message);

    }

}
