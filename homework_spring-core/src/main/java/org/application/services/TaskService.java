package org.application.services;

import lombok.Getter;
import lombok.SneakyThrows;
import org.application.models.Status;
import org.application.models.Task;
import org.springframework.stereotype.Service;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;

@Service
@Getter
public class TaskService {
    private final Map<Integer, Task> tasks = new HashMap<>();

    public Task createNewTask(Task task) {
        Task build = Task.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .deadline(task.getDeadline())
                .status(task.getStatus())
                .priority(task.getPriority())
                .build();
        return tasks.put(task.getId(), build);
    }

    public void changeStatusOfTask(Integer taskId, Status status) {
        Task task = tasks.get(taskId);
        task.setStatus(status);
    }

    public Task getTaskById(Integer idTask) {
        return tasks.get(idTask);
    }


    public <T extends Comparable<? super T>> Queue<Task> getOrderedTask(String fieldName) {
        Function<Task, T> getter = invokeGetterByField(fieldName);
        Comparator<Task> priorityComparator = (task1, task2) -> {
            T value1 = getter.apply(task1);
            T  value2 = getter.apply(task2);
            return value1.compareTo(value2);
        };
        PriorityQueue<Task> queue = new PriorityQueue<>(priorityComparator);
        queue.addAll(tasks.values());
        return queue;
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    private <T> Function<Task, T> invokeGetterByField(String fieldName) {
        PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(Task.class).getPropertyDescriptors();
        Method getter = Arrays.stream(propertyDescriptors)
                .filter(propertyDescriptor -> propertyDescriptor.getName().equals(fieldName))
                .findFirst()
                .map(PropertyDescriptor::getReadMethod)
                .orElse(null);

        if (getter != null) {
            return task -> {
                try {
                    return (T) getter.invoke(task);
                } catch (Exception e) {
                    return null;
                }
            };
        } else {
            throw new NoSuchMethodException("Getter not found for field: " + fieldName);
        }
    }
}
