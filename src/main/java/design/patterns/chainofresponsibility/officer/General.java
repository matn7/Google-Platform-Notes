package design.patterns.chainofresponsibility.officer;

import design.patterns.chainofresponsibility.message.Message;

public class General extends Officer {

    private static final int CODE = 100;
    private static final String NAME = "General";

    @Override
    public void processMessage(Message message) {
        if (message.getOfficerRank().equals(OfficerRank.GENERAL)
                && message.getCode() == CODE) {
            System.out.println(NAME + " received a message: " + message.getContent());
        } else {
            System.out.println("Wrong message recipients or wrong code!");
        }
    }
}
