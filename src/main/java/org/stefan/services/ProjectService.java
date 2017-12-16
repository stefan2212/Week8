package org.stefan.services;

import org.stefan.dao.ProjectDAO;
import org.stefan.entities.Project;
import org.stefan.utils.ValueFilter;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Map;


@Stateless
@Local
public class ProjectService {

    @EJB
    private ProjectDAO projectDAO;

    public ProjectService() {

    }

    public List<Project> search(Map<String, ValueFilter> valueFiltersSelected) {
        return projectDAO.search(valueFiltersSelected);
    }

    public int findNumberOfProjects() {
        return projectDAO.findNumberOfProjects();
    }

    public List<Project> findAll() {
        return projectDAO.findAll();
    }

    public Project findById(int id) {
        return projectDAO.findById(id);
    }

    public boolean projectIsAvailable(int id) {
        return projectDAO.projectIsAvailable(id);
    }



}
