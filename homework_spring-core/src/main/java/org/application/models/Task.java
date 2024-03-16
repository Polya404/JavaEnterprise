package org.application.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Task implements Comparable<Task> {
    private final int id;
    private int userId;
    private String name;
    private Status status;
    private String description;
    private LocalDate deadline;
    private int priority;

    @Override
    public int compareTo(Task other) {
        int priorityComparison = Integer.compare(this.priority, other.priority);
        if (priorityComparison != 0) {
            return priorityComparison;
        }

        int deadlineComparison = this.deadline.compareTo(other.deadline);
        if (deadlineComparison != 0) {
            return deadlineComparison;
        }

        int userIdComparison = Integer.compare(this.userId, other.userId);
        if (userIdComparison != 0) {
            return userIdComparison;
        }

        int nameComparison = this.name.compareTo(other.name);
        if (nameComparison != 0) {
            return nameComparison;
        }

        int statusComparison = this.status.compareTo(other.status);
        if (statusComparison != 0) {
            return statusComparison;
        }
        return this.description.compareTo(other.description);
    }
}
