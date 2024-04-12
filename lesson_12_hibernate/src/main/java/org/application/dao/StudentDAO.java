package org.application.dao;

import lombok.AllArgsConstructor;
import org.application.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaRoot;

import java.util.List;

@AllArgsConstructor
public class StudentDAO implements DAO<Student> {

    private final Session session;


    @Override
    public Student findById(long id) {
        Student student = session.get(Student.class, id);
        return student;
    }

    @Override
    public List<Student> findAll() {
//    return session.createQuery("FROM Student", Student.class).getResultList();

        JpaCriteriaQuery<Student> query = session.getCriteriaBuilder().createQuery(Student.class);
        query.select(query.from(Student.class));

        return session.createQuery(query).getResultList();
    }

    @Override
    public void add(Student entity) {
        session.persist(entity);
    }

    @Override
    public void updateById(Student entity, long id) {
        Student student = session.get(Student.class, id);

    }

    @Override
    public void deleteById(long id) {
        Student student = session.get(Student.class, id);
        session.remove(student);
    }
}
