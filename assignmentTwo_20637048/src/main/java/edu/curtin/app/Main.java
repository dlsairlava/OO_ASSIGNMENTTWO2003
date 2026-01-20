package edu.curtin.app; 

import edu.curtin.app.controller.SimulationEngine;
import edu.curtin.app.event.ConsoleLogger;
import edu.curtin.app.model.TownsInput; 

// Main entry point: Where the simulation starts 
// Gets the input form the TownsInput 

public class Main 
{
    public static void main(String [] args) throws Exception
    {
        SimulationEngine engine = new SimulationEngine(new TownsInput());
        engine.addObserver(new ConsoleLogger()); // This prints events to the console 
        engine.run(); // Begins simulation
    }
}
