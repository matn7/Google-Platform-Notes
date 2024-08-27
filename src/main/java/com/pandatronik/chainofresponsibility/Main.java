package com.pandatronik.chainofresponsibility;

import com.pandatronik.chainofresponsibility.message.Message;
import com.pandatronik.chainofresponsibility.officer.*;

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
