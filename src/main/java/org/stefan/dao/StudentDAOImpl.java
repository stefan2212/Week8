package org.stefan.dao;

import org.stefan.entities.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;


@Stateless public class StudentDAOImpl implements StudentDAO {

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
        Query query = entityManager.createQuery(
                "SELECT s.name || ' ' || s.studentsProject.size from Student s where s.studentsProject.size < (Select count(p.id) from Project p)");
        List<Student> students = query.getResultList();
        return students;
    }

    @Override public List<StudentsProject> findProjectWithStudentPreference() {

        Query query = entityManager.createQuery(
                "SELECT avg(sp.levelOfPreference) from StudentsProject sp group by sp.id.projects.id ORDER BY 1 asc ");
        List<StudentsProject> studentsProjects = query.getResultList();
        return studentsProjects;
    }

    @Override public List<Student> findStudentsWithNonePreferences() {
        return null;
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
