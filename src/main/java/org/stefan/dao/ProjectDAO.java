package org.stefan.dao;

import org.stefan.entities.Project;
import org.stefan.utils.RangeFilter;
import org.stefan.utils.ValueFilter;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;


@Local
public interface ProjectDAO {

    List<Project> search(Map<String, ValueFilter> filters);

    Integer findNumberOfProjects();

}
