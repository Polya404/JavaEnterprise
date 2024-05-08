package org.application.service;

import org.application.model.Track;
import org.application.model.TrackCoordinates;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TrackService {
    Page<Track> findAll(int page);
    Track findById(long id);
    void delete(long id);
    List<TrackCoordinates> findCoordinatesByTrackId(long trackId);
    List<TrackCoordinates> findTrackCoordinatesSortedByTime(long trackId);
}
