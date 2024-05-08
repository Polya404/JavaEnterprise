package org.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.application.model.Track;
import org.application.model.TrackCoordinates;
import org.application.repository.TrackCoordinatesRepository;
import org.application.repository.TrackRepository;
import org.application.service.TrackService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackServiceImpl implements TrackService {
    private final TrackRepository trackRepository;
    private final TrackCoordinatesRepository coordinatesRepository;

    @Override
    public Page<Track> findAll(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return trackRepository.findAll(pageable);
    }

    @Override
    public Track findById(long id) {
        return trackRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(long id) {
        trackRepository.deleteById(id);
    }

    @Override
    public List<TrackCoordinates> findCoordinatesByTrackId(long trackId) {
        return coordinatesRepository.findTrackCoordinatesByTrackId(trackId);
    }

    @Override
    public List<TrackCoordinates> findTrackCoordinatesSortedByTime(long trackId) {
        return coordinatesRepository.findTrackCoordinatesByTrackIdOrderByCreatedAt(trackId);
    }
}
