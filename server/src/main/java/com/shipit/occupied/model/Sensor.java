package com.shipit.occupied.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sensors")
public class Sensor {

    @Id
    private final String id;

    private final String position;
    private final OccupiedState occupiedState;
    private final String displayName;

    private Sensor(String id, String position, OccupiedState occupiedState, String displayName) {
        this.id = id;
        this.position = position;
        this.occupiedState = occupiedState;
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public String getDisplayName() {
        return displayName;
    }

    public OccupiedState getOccupiedState() {
        return occupiedState;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id='" + id + '\'' +
                ", position='" + position + '\'' +
                ", occupiedState=" + occupiedState +
                ", displayName='" + displayName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sensor sensor = (Sensor) o;

        if (id != null ? !id.equals(sensor.id) : sensor.id != null) return false;
        if (position != null ? !position.equals(sensor.position) : sensor.position != null) return false;
        if (occupiedState != sensor.occupiedState) return false;
        return displayName != null ? displayName.equals(sensor.displayName) : sensor.displayName == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (occupiedState != null ? occupiedState.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        return result;
    }

    public static class Builder {
        private String id;
        private String position;
        private String displayName;
        private OccupiedState occupiedState;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withPosition(String position) {
            this.position = position;
            return this;
        }

        public Builder withOccupied(OccupiedState occupiedState) {
            this.occupiedState = occupiedState;
            return this;
        }

        public Builder withDisplayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public Builder withSensor(Sensor sensor) {
            this.id = sensor.getId();
            this.position = sensor.getPosition();
            this.occupiedState = sensor.getOccupiedState();
            this.displayName = sensor.getDisplayName();
            return this;
        }

        public Sensor build() {
            return new Sensor(id, position, occupiedState, displayName);
        }
    }
}
