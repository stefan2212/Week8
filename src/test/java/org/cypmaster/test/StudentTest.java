package org.cypmaster.test;

import org.stefan.dao.StudentDAO;
import org.stefan.dao.StudentDAOImpl;
import org.stefan.entities.Student;
import org.stefan.entities.StudentsProject;
import org.stefan.utils.PersistenceUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.naming.NamingException;
import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class StudentTest {

    private static EntityManagerFactory emf;
    private static EntityManager entityManager;
    private static StudentDAO studentDAO;

    @BeforeClass
    public static void setUp() throws NamingException {
        emf = PersistenceUtil.getEntityManagerFactory(PersistenceUtil.PERSISTENCE_UNIT_TEST_NAME);
        entityManager = emf.createEntityManager();
        studentDAO = new StudentDAOImpl();
        studentDAO.setEntityManager(entityManager);
    }

    @AfterClass
    public static void onDestroy() {
        entityManager.close();
        emf.close();
    }


    @Test
    public void studentsExists() {
        List<Student> students = studentDAO.findAll();
        System.out.println(students);
        assertTrue(students.size() != 0);
    }

    @Test
    public void studentUpdate() {
        Student gabor = studentDAO.findById(2);
        gabor.setName("Silviu #Prajit Gabor");

        studentDAO.update(gabor);

        Student gaborPrajit = studentDAO.findById(2);

        Cache cache = emf.getCache();
        if (cache.contains(Student.class, 2)) {
            System.out.println("Gabor is in Cache");
        }

        assertTrue(gabor.getName().equals(gaborPrajit.getName()));
    }

    @Test
    public void getUnallocatedStudent() {
        List<Student> unallocatedStudents = studentDAO.findUnallocatedStudent();
        System.out.println(unallocatedStudents);

        assertTrue(1 == 1);
    }

    @Test
    public void getProjectWithStudentPreference() {
        List<StudentsProject> projectPreference = studentDAO.findProjectWithStudentPreference();
        System.out.println(projectPreference);
    }
}
