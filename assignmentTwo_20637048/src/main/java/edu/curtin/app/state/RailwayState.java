package edu.curtin.app.state;

import edu.curtin.app.model.Railways;

public interface RailwayState {
    void stepDay(Railways railway);
    int transport(Railways railway);
    String getDotFormat();
}
