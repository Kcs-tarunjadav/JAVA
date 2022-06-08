package com.BugTracker.Service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BugTracker.Entity.Project;
import com.BugTracker.Repository.ProjectRepository;
import com.BugTracker.Service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public Project saveProject(Project project) {

		return projectRepository.save(project);
	}

	@Override
	public List<Project> getAllProjects() {

		return projectRepository.findAll();
	}

	@Override
	public Project getProjectById(Long id) {

		return projectRepository.findById(id).get();
	}

	@Override
	public void deleteProjectById(Long id) {

		projectRepository.deleteById(id);

	}
	/*
	 * @Override public List<Project> findByProject_name(String project_name) {
	 * 
	 * return projectRepository.findByProject_Name(project_name); }
	 */

	@Override
	public List<Project> findByTechonology(String techonology) {

		return projectRepository.findByTechonology(techonology);
	}

	@Override
	public List<Project> findByStatus(String status) {

		return projectRepository.findByStatus(status);
	}

}