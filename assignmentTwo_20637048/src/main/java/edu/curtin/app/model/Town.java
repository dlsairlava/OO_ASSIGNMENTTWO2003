package edu.curtin.app.model;

import java.util.*;

/**
 * Represents a town in the simulation.
 * Tracks population, produced goods, and connected railways.
 */
public class Town {
    private final String name;
    private int population;
    private int stockpile = 0;
    private int goodsTransportedToday = 0;
    private final List<Railways> connections = new ArrayList<>();

    public Town(String name, int population) {
        this.name = name;
        this.population = population;
    }

    public void setPopulation(int newPop) {
        this.population = newPop;
    }

    public void produceGoods() {
        stockpile += population;
        goodsTransportedToday = 0; // reset counter at start of day
    }

    public int takeGoods(int max) {
        int taken = Math.min(max, stockpile);
        stockpile -= taken;
        goodsTransportedToday += taken;
        return taken;
    }

    public void receiveGoods(int amount) {
        // Received goods are consumed immediately
    }

    public void addConnection(Railways railway) {
        connections.add(railway);
    }

    public List<Railways> getConnections() {
        return connections;
    }

    public int getPopulation() {
        return population;
    }

    public int getStockpile() {
        return stockpile;
    }

    public int getGoodsTransportedToday() {
        return goodsTransportedToday;
    }

    public String getName() {
        return name;
    }
}


