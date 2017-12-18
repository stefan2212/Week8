package org.stefan.dao;

import org.stefan.entities.Student;
import org.stefan.entities.StudentsProject;
import org.stefan.utils.StudentToProjectAssignment;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import java.util.List;


@Local
public interface StudentDAO {

    List<Student> findAll();

    void delete(long id);

    void update(Student student);

    void add(Student student);

    Student findById(long id);

    List<Student> findUnallocatedStudent();

    List<String> findProjectWithStudentPreference();

    List<Student> findStudentsWithNonePreferences();

    void assignStudentsToProjects(List<StudentToProjectAssignment> assignments);

    void populate();

    void setEntityManager(EntityManager entityManager);

}
