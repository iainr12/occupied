package com.shipit.occupied.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sensors")
public class Sensor {

    @Id
    private final String id;

    private final String position;
    private final boolean occupied;
    private final String displayName;

    private Sensor(String id, String position, boolean occupied, String displayName) {
        this.id = id;
        this.position = position;
        this.occupied = occupied;
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

    public boolean isOccupied() {
        return occupied;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sensor sensor = (Sensor) o;

        if (occupied != sensor.occupied) return false;
        if (id != null ? !id.equals(sensor.id) : sensor.id != null) return false;
        if (position != null ? !position.equals(sensor.position) : sensor.position != null) return false;
        return displayName != null ? displayName.equals(sensor.displayName) : sensor.displayName == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (occupied ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id='" + id + '\'' +
                ", position='" + position + '\'' +
                ", displayName='" + displayName + '\'' +
                ", occupied=" + occupied +
                '}';
    }

    public static class Builder {
        private String id;
        private String position;
        private String displayName;
        private boolean occupied;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withPosition(String position) {
            this.position = position;
            return this;
        }

        public Builder withOccupied(boolean occupied) {
            this.occupied = occupied;
            return this;
        }

        public Builder withDisplayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public Sensor build() {
            return new Sensor(id, position, occupied, displayName);
        }
    }
}
