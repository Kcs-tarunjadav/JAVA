package com.BugTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.BugTracker.entity.Project;
import com.BugTracker.service.ProjectService;

@Controller
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@GetMapping("/addproject")
	public String project(Model model) {

		model.addAttribute("project", new Project());

		return "addproject";
	}

	@PostMapping("/addproject")
	public String addProject(Project project) {
           
		projectService.saveProject(project);
		
		return "home";
	}

}
