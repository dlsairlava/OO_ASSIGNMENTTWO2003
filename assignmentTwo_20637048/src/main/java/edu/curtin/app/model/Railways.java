package edu.curtin.app.model;

import edu.curtin.app.state.*;

public class Railways {
    private final Town townA;
    private final Town townB;
    private RailwayState state;

    public Railways(Town a, Town b) {
        this.townA = a;
        this.townB = b;
        this.state = new SingleTrackUnderConstruction(); // starts under construction
    }

    public void stepDay() {
        state.stepDay(this);
    }

    public int transportGoods() {
        return state.transport(this);
    }

    public void beginDualTrackUpgrade() {
        this.state = new DualTrackUnderConstruction();
    }

    public void setState(RailwayState newState) {
        this.state = newState;
    }

    public RailwayState getState() {
        return state;
    }

    public Town getTownA() {
        return townA;
    }

    public Town getTownB() {
        return townB;
    }

    public int transferGoods(Town from, Town to, int max) {
        int available = from.takeGoods(max);
        to.receiveGoods(available);
        return available;
    }

    public boolean connects(String name1, String name2) {
        return (townA.getName().equals(name1) && townB.getName().equals(name2)) ||
               (townA.getName().equals(name2) && townB.getName().equals(name1));
    }

    public String getDotLine() {
        return String.format("%s -- %s %s", townA.getName(), townB.getName(), state.getDotFormat());
    }
}

