package org.application.repository;

import org.application.model.TrackCoordinates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackCoordinatesRepository extends JpaRepository<TrackCoordinates, Long> {
    List<TrackCoordinates> findTrackCoordinatesByTrackId(Long trackId);
    List<TrackCoordinates> findTrackCoordinatesByTrackIdOrderByCreatedAt(Long trackId);
}
