package org.application.benchmark;

import java.util.List;
import java.util.Objects;

public class Container {
    private final List<Object> objectList;

    public Container(List<Object> objectList) {
        this.objectList = objectList;
    }

    public List<Object> getObjectList() {
        return objectList;
    }
}
