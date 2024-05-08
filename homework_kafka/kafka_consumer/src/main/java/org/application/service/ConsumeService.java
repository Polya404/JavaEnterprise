package org.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.application.model.Track;
import org.application.model.TrackCoordinates;
import org.application.repository.TrackRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumeService {

    private final TrackService trackService;
    private final TrackRepository trackRepository;

    @KafkaListener(topics = "topic")
    public void receiveMessage(ConsumerRecord<String, String> record) {
        log.info("receive message: {}", record);
        Long trackId = Long.parseLong(record.key());
        Track track = trackRepository.findById(trackId).orElse(new Track());
        if (track.getId() == null) {
            track.setId(trackId);
        }
        TrackCoordinates coordinates = trackService.parse(record.value());
        track.getTrackCoordinatesList().add(coordinates);
        coordinates.setTrack(track);
        trackService.save(track);
        log.info("Successfully received message: {}", record);
        log.info("Successfully saved track: {}", track);
    }
}
