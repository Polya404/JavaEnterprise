package org.application.models;

import lombok.Getter;

@Getter
public enum Status {
    TO_DO(0), IN_PROGRESS(1), DONE(2), BLOCKED(3);
    private final int priority;

    Status(int priority) {
        this.priority = priority;
    }
}
