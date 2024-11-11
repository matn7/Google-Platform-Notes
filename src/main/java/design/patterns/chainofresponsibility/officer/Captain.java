package design.patterns.chainofresponsibility.officer;

import design.patterns.chainofresponsibility.message.Message;

public class Captain extends Officer {

    private static final int CODE = 50;
    private static final String NAME = "Captain";

    @Override
    public void processMessage(Message message) {
        if (message.getOfficerRank().equals(OfficerRank.CAPTAIN)
                && message.getCode() == CODE) {
            System.out.println(NAME + " received a message: " + message.getContent());
        } else {
            getSuperiorOfficer().processMessage(message);
        }
    }
}
