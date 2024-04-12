package org.application.dao;

import lombok.AllArgsConstructor;
import org.application.entity.Course;
import org.hibernate.Session;

import java.util.List;

@AllArgsConstructor
public class CourseDAO implements DAO<Course> {

    private final Session session;


    @Override
    public Course findById(long id) {
        Course course = session.get(Course.class, id);
        return course;
    }

    @Override
    public List<Course> findAll() {
        return session.createQuery("FROM Course", Course.class).getResultList();
    }

    @Override
    public void add(Course entity) {
        session.persist(entity);
    }

    @Override
    public void updateById(Course entity, long id) {
        Course course = session.get(Course.class, id);

    }

    @Override
    public void deleteById(long id) {
        Course course = session.get(Course.class, id);
        session.remove(course);
    }
}
