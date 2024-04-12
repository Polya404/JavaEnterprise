package org.application.repository;

import org.application.dto.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentDAO extends JpaRepository<Student, Long> {

//  @Query(value = "SELECT * FROM student AS s", nativeQuery = true)
//  List<Student> findAll();


    Student getHilelelGoodStudentsByNameAndEmail(String name, String email);
}
