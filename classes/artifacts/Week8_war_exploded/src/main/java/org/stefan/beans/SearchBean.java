package org.stefan.beans;

import org.stefan.entities.Project;
import org.stefan.services.ProjectService;
import org.stefan.utils.ValueFilter;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@ManagedBean(name = "searchBean")
@ViewScoped
public class SearchBean implements Serializable {

    private final static Long serialVersionUID = 1L;

    @EJB
    private ProjectService projectService;

    private final static String PROJECT_FILTER_NAME = "FILTER_BY_NAME";
    private final static String PROJECT_FILTER_DESCRIPTION = "FILTER_BY_DESCRIPTION";
    private Map<String, ValueFilter> valueFilters;
    private List<Project> projects;
    private boolean showResultProjectTable;

    @PostConstruct
    public void init() {
        this.valueFilters = new HashMap<>();
        this.valueFilters.put(PROJECT_FILTER_NAME, new ValueFilter("", false));
        this.valueFilters.put(PROJECT_FILTER_DESCRIPTION, new ValueFilter("", false));
        this.projects = new ArrayList<>();
        this.showResultProjectTable = false;

    }


    public void search(ActionEvent actionEvent) {
        Map<String, ValueFilter> valueFiltersSelected = valueFilters.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSelected())
                .collect(Collectors.toMap(key -> key.getKey(), value -> value.getValue()));

        projects = projectService.search(valueFiltersSelected);
        //addMessage("Projects Found:" + projects.size());
        if (projects.size() > 0) {
            showResultProjectTable = true;
        } else {
            showResultProjectTable = false;
        }
        System.out.println("Results:" + projects);
    }


    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


    public Map<String, ValueFilter> getValueFilters() {
        return valueFilters;
    }

    public void setValueFilters(Map<String, ValueFilter> valueFilters) {
        this.valueFilters = valueFilters;
    }


    public String getProjectFilterName() {
        return PROJECT_FILTER_NAME;
    }

    public String getProjectFilterDescription() {
        return PROJECT_FILTER_DESCRIPTION;
    }


    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public boolean isShowResultProjectTable() {
        return showResultProjectTable;
    }

    public void setShowResultProjectTable(boolean showResultProjectTable) {
        this.showResultProjectTable = showResultProjectTable;
    }
}
