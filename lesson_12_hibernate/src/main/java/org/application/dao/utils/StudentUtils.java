package org.application.dao.utils;

import org.application.entity.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentUtils {

    public void addCourse(List<Course> courseList, Course course) {
        if (Objects.isNull(courseList)) {
            courseList = new ArrayList<>();
        }
        courseList.add(course);
    }
}
