package com.shipit.occupied.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "states")
public class State {

    private final String sensorId;
    private final OccupiedState occupiedState;
    private final Date recordedAt;
    private final Source source;

    private State(String sensorId, OccupiedState occupiedState, Date recordedAt, Source source) {
        this.sensorId = sensorId;
        this.occupiedState = occupiedState;
        this.recordedAt = recordedAt;
        this.source = source;
    }

    public String getSensorId() {
        return sensorId;
    }

    public OccupiedState getOccupiedState() {
        return occupiedState;
    }

    public Date getRecordedAt() {
        return recordedAt;
    }

    public Source getSource() {
        return source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        if (sensorId != null ? !sensorId.equals(state.sensorId) : state.sensorId != null) return false;
        if (occupiedState != state.occupiedState) return false;
        if (recordedAt != null ? !recordedAt.equals(state.recordedAt) : state.recordedAt != null) return false;
        return source == state.source;
    }

    @Override
    public int hashCode() {
        int result = sensorId != null ? sensorId.hashCode() : 0;
        result = 31 * result + (occupiedState != null ? occupiedState.hashCode() : 0);
        result = 31 * result + (recordedAt != null ? recordedAt.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "State{" +
                "sensorId='" + sensorId + '\'' +
                ", occupiedState=" + occupiedState +
                ", recordedAt=" + recordedAt +
                ", source=" + source +
                '}';
    }

    public static class Builder {

        private String sensorId;
        private OccupiedState occupiedState;
        private Date recordedAt;
        private Source source;

        public Builder withSensorId(String sensorId) {
            this.sensorId = sensorId;
            return this;
        }

        public Builder withOccupiedState(OccupiedState occupiedState) {
            this.occupiedState = occupiedState;
            return this;
        }

        public Builder withRecordedAt(Date recordedAt) {
            this.recordedAt = recordedAt;
            return this;
        }

        public Builder withSource(Source source) {
            this.source = source;
            return this;
        }

        public State build() {
            return new State(sensorId, occupiedState, recordedAt, source);
        }
    }
}
