package org.stefan.services;

import org.stefan.dao.StudentDAO;
import org.stefan.entities.Student;
import org.stefan.entities.StudentsProject;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;


@Stateless
@Local
public class StudentService {

    private static StudentService studentService;

    @EJB
    private StudentDAO studentDAO;

    public StudentService() {
    }

    public synchronized static StudentService getInstance() {
        if (studentService == null) {
            studentService = new StudentService();
        }
        return studentService;
    }

    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    public List<Student> getUnallocatedStudent() {
        return studentDAO.findUnallocatedStudent();
    }

    public List<String> getProjectsWithStudentPreference() {
        return studentDAO.findProjectWithStudentPreference();
    }

    public void populate() {
        studentDAO.populate();
    }

}
