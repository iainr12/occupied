package com.shipit.occupied.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "locations")
public class Location {

    @Id
    private final String id;

    private final String floor;
    private final String zone;
    private final LocationType type;
    private final Gender gender;
    private final String displayName;
    private final List<String> sensors;

    @JsonProperty("sensors")
    @Transient
    private List<Sensor> sensorData;

    private Location(String id, String floor, String zone, LocationType type, Gender gender, String displayName, List<String> sensors) {
        this.id = id;
        this.floor = floor;
        this.zone = zone;
        this.type = type;
        this.gender = gender;
        this.displayName = displayName;
        this.sensors = sensors;
    }

    public String getId() {
        return id;
    }

    public String getFloor() {
        return floor;
    }

    public String getZone() {
        return zone;
    }

    public LocationType getType() {
        return type;
    }

    public Gender getGender() {
        return gender;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<String> getSensorIds() {
        return sensors;
    }

    @JsonIgnore
    public List<Sensor> getSensors() {
        return sensorData;
    }

    public void setSensorData(List<Sensor> sensorData) {
        this.sensorData = sensorData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (id != null ? !id.equals(location.id) : location.id != null) return false;
        if (floor != null ? !floor.equals(location.floor) : location.floor != null) return false;
        if (zone != null ? !zone.equals(location.zone) : location.zone != null) return false;
        if (type != location.type) return false;
        if (gender != location.gender) return false;
        if (displayName != null ? !displayName.equals(location.displayName) : location.displayName != null)
            return false;
        return sensors != null ? sensors.equals(location.sensors) : location.sensors == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (floor != null ? floor.hashCode() : 0);
        result = 31 * result + (zone != null ? zone.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (sensors != null ? sensors.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id='" + id + '\'' +
                ", floor='" + floor + '\'' +
                ", zone='" + zone + '\'' +
                ", type=" + type +
                ", gender=" + gender +
                ", displayName='" + displayName + '\'' +
                ", sensors=" + sensors +
                '}';
    }

    public static class Builder {
        private String id;
        private String floor;
        private String zone;
        private LocationType type;
        private Gender gender;
        private String displayName;
        private List<String> sensors;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withFloor(String floor) {
            this.floor = floor;
            return this;
        }

        public Builder withZone(String zone) {
            this.zone = zone;
            return this;
        }

        public Builder withLocationType(LocationType type) {
            this.type = type;
            return this;
        }

        public Builder withGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder withDisplayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public Builder withSensors(List<String> sensors) {
            this.sensors = sensors;
            return this;
        }

        public Location build() {
            return new Location(id, floor, zone, type, gender, displayName, sensors);
        }
    }
}
