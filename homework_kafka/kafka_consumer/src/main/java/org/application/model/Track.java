package org.application.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Track {
    @Id
    private Long id;

    @OneToMany(mappedBy = "track", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrackCoordinates> trackCoordinatesList = new ArrayList<>();
}
