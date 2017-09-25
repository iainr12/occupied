package com.shipit.occupied.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "states")
public class State {

    private final String sensorId;
    private final boolean occupied;
    private final Date recordedAt;
    private final Source source;

    private State(String sensorId, boolean occupied, Date recordedAt, Source source) {
        this.sensorId = sensorId;
        this.occupied = occupied;
        this.recordedAt = recordedAt;
        this.source = source;
    }

    public String getSensorId() {
        return sensorId;
    }

    public boolean isOccupied() {
        return occupied;
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

        State state1 = (State) o;

        if (occupied != state1.occupied) return false;
        if (sensorId != null ? !sensorId.equals(state1.sensorId) : state1.sensorId != null) return false;
        if (recordedAt != null ? !recordedAt.equals(state1.recordedAt) : state1.recordedAt != null) return false;
        return source == state1.source;
    }

    @Override
    public int hashCode() {
        int result = sensorId != null ? sensorId.hashCode() : 0;
        result = 31 * result + (occupied ? 1 : 0);
        result = 31 * result + (recordedAt != null ? recordedAt.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "State{" +
                "sensorId='" + sensorId + '\'' +
                ", occupied=" + occupied +
                ", recordedAt=" + recordedAt +
                ", source=" + source +
                '}';
    }
}
