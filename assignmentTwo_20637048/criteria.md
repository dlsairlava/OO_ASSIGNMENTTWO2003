## Justification of Project Design 
### State Pattern 
The state pattern in my project is used to manage the dynamic behaviour of railways systems in the town based on two status: operational or on construction. In my project I have implemented the state pattern to the railway system to encapsulate the state specific behaviour that is present. The state behaviours are single track under construction, single track operational, dual track under construction and dual track operational. These four classes are implemented by Railway state class. Methods such as transportGoods() and stepDay() are called simultaneously preventing SimulationEngine or Railways to worry about how the state behaves interanlly. This idea was chosen for two reasons: to make it easier to add new types of railway states without the need to modify existing logic and also each class having its own behvaiour relevant to the state. 

### Observer Pattern 
The observer pattern in my project was used to allow the SimulationEngine to emit simulation such as day start, messages without embedding how they are displayed. In my program, SimulationEngine calls the method notifyObservers each day for a new message. The logger is also an observer that listens and logs to the console. 

### Purpose of each file 
In my project, majority of the files are in packages, below I are each package and the files present. Each file had a goal. 

Main.java 
This file is not in a package. 
The purpose of this file is to create the entry point for my project.
Main. java creates the simulation engine whilst adding the observes to therfore start the simulation loop to print out the messages.

#### Controller Package 
SimulationEngine.java
This is the main file of my simulation. This file attains messages from TownsInput. It also updates the town/railway model based on the messages. 
The simulation process involves processing the goods production, transport and then construction. It also triggers the console and file output.

#### Model Package 
Town.java 
This file represents all the towns in the simulation. Town.java tracks the name, population, the stockpile of goods and the list of connected railways.The methods present in this file are the goods produced each day, somcumption of stockpiled goods and the management of connection.

Railways.java 
Railways.java represents the railway section that is present two towns. This file holds the reference to the two towns, it displays the current state and the methods luke step day and transport goods. 
-> Involoved in the state pattern through RailwayState.java 

#### State Package 
This is package that implements the state pattern for railway transitions.

RailwayState.java 
This file defines all the railway behaviour presents. This files contaisn methids such as transport() and stepDay().

SingleTrackUnderConstruction.java
This is the state when a railway is stillbeing built. It doesnt transport goods and after five days becomes single track operational.

DualTrackUnderCondition.java
This is the stae when a dual-track is in progress. It acts as a single track until the five day updgrade and then it becomes a dual track operational.

SingleTrackOperational.java 
This is the state of working single track railway. It can transport goods one direction per day, can upgrade to a dual track and direction toggles each day. 

DualTrackOperational.java
This state is when a track is a fully operational dual track that can transport good both ways.

#### Event package
The Event package uses an observer to log and react to events

Observer.java 
This is the interface the method update is seen which updates the string message. 

ConsoleLogger.java 
This is concrete observer that uses java.util.logging. It logs messages from each simulation by step and event. 

#### View package 
This package handles all the output and display concerns.

ConsolePrinter.java 
ConsolePrinter.java prints out a summary of each town state each day. This involves population, single/dual railway counts and goods stockpile

DotOutputGenerator.java 
This file generates a dot file to vsilaise the town and railway network. It reflects contruction ststus and overwrites the file. 
