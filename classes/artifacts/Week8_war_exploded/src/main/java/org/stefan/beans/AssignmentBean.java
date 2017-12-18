package org.stefan.beans;

import org.stefan.services.ProjectService;
import org.stefan.services.StudentService;
import org.stefan.utils.StudentToProjectAssignment;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.List;


@Stateful
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class AssignmentBean {

    @PostConstruct
    public void init() {
    }

    @EJB
    private StudentService studentService;

    public void checkAssignmentProjectsToStudents(List<StudentToProjectAssignment> assignments)
    {
        studentService.assignStudentsToProjects(assignments);
    }
}
