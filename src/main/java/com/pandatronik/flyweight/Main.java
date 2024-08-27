package com.pandatronik.flyweight;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        List<Object> activeUnits = new ArrayList<>();

        IntStream.rangeClosed(0, 1000).forEach(unit -> {
            activeUnits.add(new TeslTank(0, 0));
            activeUnits.add(new Rifleman(0, 0));
            activeUnits.add(new Destroyer(0, 0));
        });

        System.out.println();

    }

}
