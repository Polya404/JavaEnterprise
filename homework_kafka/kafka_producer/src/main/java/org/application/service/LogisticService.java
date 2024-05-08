package org.application.service;

import lombok.RequiredArgsConstructor;
import org.application.model.Track;
import org.application.model.TrackCoordinates;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class LogisticService {
    private final TrackService trackService;

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);

    public void sendParallelTracks() {
        for (int i = 0; i < 10; i++) {
            final int trackIndex = i;
            executorService.scheduleAtFixedRate(() -> {
                Track track = new Track((long) (trackIndex + 1),
                        new TrackCoordinates(36.385913, -127.441406));
                trackService.sendNewCoordinates(track);
            }, 0, 10, TimeUnit.SECONDS);
        }
    }

    public void stopSending() {
        executorService.shutdown();
    }
}
