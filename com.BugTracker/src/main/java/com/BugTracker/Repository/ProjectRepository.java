package com.BugTracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BugTracker.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

	
	List<Project> findByTechonology(String techonology);

	List<Project> findByStatus(String status);
}
