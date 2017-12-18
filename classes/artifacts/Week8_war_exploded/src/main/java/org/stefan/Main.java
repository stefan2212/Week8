package org.stefan;

import org.stefan.dto.StudentPreferenceDTO;
import org.stefan.entities.Project;
import org.stefan.entities.Student;
import org.stefan.entities.StudentsProject;
import org.stefan.services.StudentService;

import java.util.*;


public class Main {


    public static StudentService studentService =  StudentService.getInstance();


    public static void getStudents() {

    }

    public static void getProjectsWithStudentPreference() {


    }

    public static void populate() {
        studentService.populate();
    }

    public static void getUnallocatedStudent() {
        List<Student> unallocatedStudents = studentService.getUnallocatedStudent();
        System.out.println(unallocatedStudents);
    }


    public static void main(String[] args) {
        getStudents();
        System.out.println();
        System.out.println("Unallocated students");
        getUnallocatedStudent();
        System.out.println();
        System.out.println("Projects with student preference");
        getProjectsWithStudentPreference();
    }
}
