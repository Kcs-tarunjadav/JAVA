package com.BugTracker.Service;

import java.util.List;

import com.BugTracker.Entity.Project;

public interface ProjectService {

	Project saveProject(Project project);

	List<Project> getAllProjects();

	Project getProjectById(Long id);

	void deleteProjectById(Long id);

//	List<Project> findByProject_name(String project_name);

	List<Project> findByTechonology(String techonology);

	List<Project> findByStatus(String status);
}
