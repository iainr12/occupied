package com.shipit.occupied.model;

public class StateResponse {

    private OccupiedState occupiedState;

    private StateResponse(OccupiedState occupiedState) {
        this.occupiedState = occupiedState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StateResponse that = (StateResponse) o;

        return occupiedState == that.occupiedState;
    }

    @Override
    public int hashCode() {
        return occupiedState != null ? occupiedState.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "StateResponse{" +
                "occupiedState=" + occupiedState +
                '}';
    }

    public static class Builder {

        private OccupiedState occupiedState;

        public Builder withOccupiedState(OccupiedState occupiedState) {
            this.occupiedState = occupiedState;
            return this;
        }

        public StateResponse build() {
            return new StateResponse(occupiedState);
        }
    }
}
