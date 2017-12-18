package org.stefan.dao;

import org.stefan.entities.*;
import org.stefan.utils.StudentToProjectAssignment;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)

public class StudentDAOImpl implements StudentDAO {

    @PersistenceContext(unitName = "Week7") private EntityManager entityManager;

    public StudentDAOImpl() {
        //        this.entityManager = PersistenceUtil.getEntityManager();
    }

    @Override public List<Student> findAll() {
        Query query = entityManager.createQuery("FROM Student");
        List<Student> students = query.getResultList();

        return students;
    }

    @Override public void delete(long id) {

        Optional<Student> student = Optional.ofNullable(entityManager.find(Student.class, id));
        if (student.isPresent()) {
            entityManager.remove(student.get());
        }

    }

    @Override public void update(Student student) {

        Optional<Student> aux = Optional.ofNullable(entityManager.find(Student.class, student.getId()));
        if (aux.isPresent()) {
            entityManager.merge(student);
        }
    }

    @Override public void add(Student student) {
        entityManager.persist(student);
    }

    @Override public Student findById(long id) {
        return entityManager.find(Student.class, id);
    }

    @Override public List<Student> findUnallocatedStudent() {
        return null;
    }

    @Override
    public List<String> findProjectWithStudentPreference() {

        Query query = entityManager.createQuery(
                "SELECT concat(avg(sp.levelOfPreference),' ', sp.id.projects.name) from StudentsProject sp " +
                        "group by sp.id.projects.id,sp.id.projects.name " +
                        "ORDER BY 1 asc ");
        List<String> studentsProjects = query.getResultList();
        return studentsProjects;
    }

    @Override public List<Student> findStudentsWithNonePreferences() {
        return null;
    }

    @Override
    public void assignStudentsToProjects(List<StudentToProjectAssignment> assignments) {
        try {
            for (StudentToProjectAssignment currentAssign : assignments) {
                Student student = entityManager.find(Student.class,(long) currentAssign.getStudentId());
                Project project = entityManager.find(Project.class,currentAssign.getProjectId());

                student.setAssignedProject(project);
                project.getAssignedStudent().add(student);

                entityManager.persist(student);
                entityManager.persist(project);

            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override public void populate() {
        Skill skill1 = new Skill();
        skill1.setName("JavaEE");
        Student student1 = new Student();
        student1.setName("Vasiliu Alexandru");
        student1.setEmail("l@mail.com");
        Student student2 = new Student();
        student2.setName("Gabor Silviu");
        student2.setEmail("g@mail.com");
        Project project1 = new Project();
        project1.setName("Java EE Project");
        project1.setDescription("Some Description");
        project1.setCapacity(2);
        ProjectSkills projectSkills1_1 = new ProjectSkills();
        projectSkills1_1.setPrimaryKey(new ProjectSkillId());
        projectSkills1_1.setProject(project1);
        projectSkills1_1.setSkill(skill1);
        projectSkills1_1.setLevelOfPreference(0);
        entityManager.getTransaction().begin();

        try {
            entityManager.persist(student1);
            entityManager.persist(student2);
            entityManager.persist(skill1);
            entityManager.persist(project1);
            entityManager.persist(projectSkills1_1);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                System.out.println("Transaction active");
                entityManager.getTransaction().rollback();
            }
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
