package com.pandatronik.adapter;

public class Main {

    public static void main(String[] args) {
        ContinentalSocket continentalSocket = new ContinentalSocket();
        UKSocket ukSocket = new UKSocket();

        UKDevice ukRadio = () -> System.out.println("Music plays UK");
        ukSocket.plugIn(ukRadio);

        ContinentalDevice continentalRadio = () -> System.out.println("Music plays UK");

        TwoWayAdapter adapter = new TwoWayAdapter(ukRadio, continentalRadio);
        continentalSocket.plugIn(adapter);
        ukSocket.plugIn(adapter);
    }

}
