package org.application.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TrackCoordinates {
    public final double latitude;
    public final double longitude;

    public TrackCoordinates(double lat, double lon) {
        latitude = lat;
        longitude = lon;
    }
}
