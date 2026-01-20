package edu.curtin.app.view;

import edu.curtin.app.model.Town;

import java.util.Map;

// Responsible for printing town stats to the console each day.

public class ConsolePrinter {
    public void print(Map<String, Town> towns, int day) {
        System.out.println("Day " + day + ":");
        for (Town town : towns.values()) {
            System.out.printf("%s p:%d rs:%d rd:%d gs:%d gt:%d%n",
                town.getName(),
                town.getPopulation(),
                (int) town.getConnections().stream().filter(r -> r.getDotLine().contains("style=\"dashed\"]")).count(),
                (int) town.getConnections().stream().filter(r -> r.getDotLine().contains("color=\"black:black\"]")).count(),
                town.getStockpile(),
                town.getGoodsTransportedToday()
            );
        }
    }
}
