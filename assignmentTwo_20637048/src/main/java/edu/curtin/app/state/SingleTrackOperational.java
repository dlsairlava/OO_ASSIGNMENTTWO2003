package edu.curtin.app.state;

import edu.curtin.app.model.Railways;
import edu.curtin.app.model.Town;

public class SingleTrackOperational implements RailwayState {
    private boolean directionAtoB = true;
    private final Town townA;
    private final Town townB;

    public SingleTrackOperational(Town a, Town b) {
        this.townA = a;
        this.townB = b;
    }

    @Override
    public void stepDay(Railways railway) {
        directionAtoB = !directionAtoB;
    }

    @Override
    public int transport(Railways railway) {
        if (directionAtoB) {
            return railway.transferGoods(townA, townB, 100);
        } else {
            return railway.transferGoods(townB, townA, 100);
        }
    }

    @Override
    public String getDotFormat() {
        return "";
    }
}
