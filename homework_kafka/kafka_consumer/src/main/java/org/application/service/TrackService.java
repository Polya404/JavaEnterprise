package org.application.service;

import lombok.RequiredArgsConstructor;
import org.application.model.Track;
import org.application.model.TrackCoordinates;
import org.application.repository.TrackCoordinatesRepository;
import org.application.repository.TrackRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TrackService {
    private final TrackRepository trackRepository;
    private final TrackCoordinatesRepository coordinatesRepository;

    @Transactional
    public Track save(Track track) {
        Track saved = trackRepository.save(track);
        coordinatesRepository.save(saved.getTrackCoordinatesList().getFirst());
        return saved;
    }

    public TrackCoordinates parse(String input) {
        TrackCoordinates coordinates = new TrackCoordinates();
        coordinates.setLatitude(getLatitude(input));
        coordinates.setLongitude(getLongitude(input));
        coordinates.setCreatedAt(LocalDateTime.now());
        return coordinates;
    }

    private double getLongitude(String input) {
        int startIndex = input.indexOf("longitude=") + "longitude=".length();
        int endIndex = input.indexOf(")", startIndex);
        return Double.parseDouble(input.substring(startIndex, endIndex).trim());
    }

    private double getLatitude(String input) {
        int startIndex = input.indexOf("latitude=") + "latitude=".length();
        int endIndex = input.indexOf(",", startIndex);
        return Double.parseDouble(input.substring(startIndex, endIndex).trim());
    }
}
