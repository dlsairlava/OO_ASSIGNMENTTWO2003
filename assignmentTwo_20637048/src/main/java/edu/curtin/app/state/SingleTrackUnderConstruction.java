package edu.curtin.app.state;

import edu.curtin.app.model.Railways;

public class SingleTrackUnderConstruction implements RailwayState {
    private int daysRemaining = 5;

    @Override
    public void stepDay(Railways railway) {
        if (--daysRemaining == 0) {
            railway.setState(new SingleTrackOperational(railway.getTownA(), railway.getTownB()));
        }
    }

    @Override
    public int transport(Railways railway) {
        return 0; // not ready
    }

    @Override
    public String getDotFormat() {
        return "[style=\"dashed\"]";
    }
}
