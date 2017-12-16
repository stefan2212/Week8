package org.stefan.timers;

import org.stefan.dao.StudentDAO;

import javax.ejb.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;


@Singleton
@LocalBean
@Startup
public class TimerSessionBean {

    @EJB
    private StudentDAO studentDAO;

    private final static String PDF_REPORT_PATH = ".";

    @Schedule(second = "13,34,57", minute = "*", hour = "*")
    public void execute(Timer timer) {
        System.out.println("Executing ...");
        System.out.println("Execution Time : " + new Date());
        try {
            writeHtmlPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Generated Report");
        System.out.println("____________________________________________");
    }

    private void writeHtmlPage() throws Exception {
        BufferedWriter br = new BufferedWriter(new FileWriter("fisier.html"));
        br.write("caca");
        br.close();
        createTable();
    }

    private void addEmptyLine() {

    }

    private void createTable() {

        List<String> studentsProjects = studentDAO.findProjectWithStudentPreference();
        studentsProjects.forEach(info -> {
            String[] data = info.split(" ", 2);

        });

    }

}
