package com.BugTracker.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BugTracker.Entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

	//List<Project> findByProject_Name(String project_name);

	List<Project> findByTechonology(String techonology);

	List<Project> findByStatus(String status);
}
