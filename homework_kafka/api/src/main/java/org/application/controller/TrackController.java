package org.application.controller;

import lombok.RequiredArgsConstructor;
import org.application.model.Track;
import org.application.model.TrackCoordinates;
import org.application.service.TrackService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/track")
@RequiredArgsConstructor
public class TrackController {
    private final TrackService trackService;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public Page<Track> getAllTrack(@RequestParam(defaultValue = "0") int page) {
        return trackService.findAll(page);
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Track getTrackById(@PathVariable Long id) {
        return trackService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTrackById(@PathVariable Long id) {
        trackService.delete(id);
    }

    @GetMapping("/coordinates/get/{trackId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TrackCoordinates> getTrackCoordinatesByTrackId(@PathVariable Long trackId) {
        return trackService.findCoordinatesByTrackId(trackId);
    }

    @GetMapping("/coordinates/get/ordered/{trackId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TrackCoordinates> getTrackCoordinatesByTrackIdOrderedByCreatedAt(@PathVariable Long trackId) {
        return trackService.findTrackCoordinatesSortedByTime(trackId);
    }
}
