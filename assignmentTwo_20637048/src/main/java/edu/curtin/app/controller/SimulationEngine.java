package edu.curtin.app.controller;

import edu.curtin.app.event.*;
import edu.curtin.app.event.Observer;
import edu.curtin.app.model.*;
import edu.curtin.app.view.*;

import java.util.*;

public class SimulationEngine {

    // Variables 
    private final Map<String, Town> towns = new HashMap<>(); 
    private final List<Railways> railways = new ArrayList<>(); 
    private final List<Observer> observers = new ArrayList<>(); 
    private final TownsInput input; 
    private final DotOutputGenerator dotGen = new DotOutputGenerator();
    private final ConsolePrinter consolePrinter = new ConsolePrinter(); 

    //Assigning 
    public SimulationEngine(TownsInput input)
    {
        this.input = input;
    }

    public void addObserver(Observer o)
    {
        observers.add(o);
    }

     private void notifyObservers(String msg) {
        observers.forEach(o -> o.update(msg));
    }

    // Produces the message output 
    public void run() throws Exception 
    {
        int day = 0;
        while (System.in.available() == 0) 
        {
            day++;
            notifyObservers("\n---\nDay " + day + ":\n");

            // Handle all messages for this day
            String msg;
            while ((msg = input.nextMessage()) != null) {
                notifyObservers(msg);
                processMessage(msg);
            }

            // Value -> Towns produce goods
            towns.values().forEach(Town::produceGoods);

            // Goods that are transported through the railways 
            railways.forEach(Railways::transportGoods);

            // Advance construction / toggle direction
            railways.forEach(Railways::stepDay);

            // This prints out the state of the towns, days and railways
            consolePrinter.print(towns, day);
            dotGen.generateDot(towns, railways);

            Thread.sleep(1000); // This is for the 1 second time break 
            // One second = one day 
        }
    }

    // Prints out the format of the message
    private void processMessage(String msg) 
    {
        String[] parts = msg.split(" ");
        if (parts.length != 3) return;

        String type = parts[0];
        String name1 = parts[1];
        String value = parts[2];

        switch (type) {
            case "town-founding" -> 
            {
                try 
                {
                    int pop = Integer.parseInt(value);
                    towns.putIfAbsent(name1, new Town(name1, pop));
                } catch (NumberFormatException ignored) {}
            }
            case "town-population" -> 
            {
                try 
                {
                    int pop = Integer.parseInt(value);
                    if (towns.containsKey(name1)) 
                    {
                        towns.get(name1).setPopulation(pop);
                    }
                } catch (NumberFormatException ignored) {}
            }
            case "railway-construction", "railway-duplication" -> {
                Town t1 = towns.get(name1);
                Town t2 = towns.get(value);
                if (t1 == null || t2 == null) return;

                Optional<Railways> existing = railways.stream()
                        .filter(r -> r.connects(t1.getName(), t2.getName()))
                        .findFirst();

                if (type.equals("railway-construction") && existing.isEmpty()) {
                    Railways r = new Railways(t1, t2);
                    railways.add(r);
                    t1.addConnection(r);
                    t2.addConnection(r);
                } else if (type.equals("railway-duplication") && existing.isPresent()) {
                    existing.get().beginDualTrackUpgrade();
                }
            }
        }
    }

}


