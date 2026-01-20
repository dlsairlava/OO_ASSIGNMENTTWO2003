package edu.curtin.app.state;

import edu.curtin.app.model.Railways;

public class DualTrackUnderConstruction implements RailwayState {
    private int daysRemaining = 5;

    @Override
    public void stepDay(Railways railway) {
        if (--daysRemaining == 0) {
            railway.setState(new DualTrackOperational());
        }
    }

    @Override
    public int transport(Railways railway) {
        return 100; // still just one-direction allowed
    }

    @Override
    public String getDotFormat() {
        return "[style=\"dashed\",color=\"black:black\"]";
    }
}
