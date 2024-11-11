package interview.solid.L;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<TournamentJoiner> members = List.of(
                new PremiumMember("Jack Hores"),
                new PremiumMember("Tom Johns"),
                new FreeMember("Mertin Vilop")
        );

        for (TournamentJoiner member : members) {
            member.joinTournament();
        }

        List<TournamentOrganizer> premiumMembers = List.of(
                new PremiumMember("Jack Hores"),
                new PremiumMember("Tom Johns")
        );

        for (TournamentOrganizer member : premiumMembers) {
            member.organizeTournament();
        }
    }

}
