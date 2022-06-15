package com.BugTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.BugTracker.entity.Project;
import com.BugTracker.entity.Team;
import com.BugTracker.service.ProjectService;
import com.BugTracker.service.TeamService;

@Controller
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private TeamService teamService;
	
	
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
	
	@GetMapping("/showproject")
	public String showProjects(Model model) {
		
		model.addAttribute("project",projectService.getAllProjects());
		
		return "project_View";
	}
	
	@GetMapping("/project/addteam/{id}")
	public String showAvailableTeams(@PathVariable Long id ,Model model) {
		
		model.addAttribute("project",projectService.getProjectById(id));
		model.addAttribute("team", teamService.getAllTeams());
		
		
	return "assignproject";
	}
	
	@GetMapping("project/addteam/team/add/{id}/{projectid}")
	public String assignTeam(@PathVariable("id")Long id,@PathVariable("projectid")Long projectid,Project project,Team team) {
		
		team=teamService.getTeamById(id);
		project=projectService.getProjectById(projectid);
		
		project.getTeams().add(team);
		team.getProjects().add(project);
		
		projectService.saveProject(project);
		teamService.saveTeam(team);
	
		return "redirect:/showproject?added";
	}

}
