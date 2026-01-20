package edu.curtin.app.state;

import edu.curtin.app.model.Railways;
import edu.curtin.app.model.Town;

public class DualTrackOperational implements RailwayState {
    @Override
    public void stepDay(Railways railway) {
        // Bidirectional and stable — nothing to change daily
    }

    @Override
    public int transport(Railways railway) {
        Town a = railway.getTownA();
        Town b = railway.getTownB();

        int sentAtoB = railway.transferGoods(a, b, 100);
        int sentBtoA = railway.transferGoods(b, a, 100);
        return sentAtoB + sentBtoA;
    }

    @Override
    public String getDotFormat() {
        return "[color=\"black:black\"]";
    }
}
