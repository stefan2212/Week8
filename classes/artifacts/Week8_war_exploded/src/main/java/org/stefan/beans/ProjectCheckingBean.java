package org.stefan.beans;

import org.stefan.dao.ProjectDAO;
import org.stefan.services.ProjectService;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;


@Stateless
@LocalBean
public class ProjectCheckingBean {

    @EJB
    private ProjectService projectService;


    public boolean projectIsAvailable(int id) {
        return projectService.projectIsAvailable(id);
    }

}
