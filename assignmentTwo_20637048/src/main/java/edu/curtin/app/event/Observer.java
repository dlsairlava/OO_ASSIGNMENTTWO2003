package edu.curtin.app.event;
// This interface is where the simulation receives the messages like i.e logging and monitoring 
public interface Observer
{

    void update(String message);
    
}
