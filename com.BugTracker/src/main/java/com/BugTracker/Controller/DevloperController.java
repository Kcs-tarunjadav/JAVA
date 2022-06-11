package com.BugTracker.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.BugTracker.entity.Team;
import com.BugTracker.entity.User;
import com.BugTracker.service.TeamService;
import com.BugTracker.service.UserService;

@Controller
public class DevloperController {

	@Autowired
	private UserService userService;

	@Autowired
	private TeamService teamService;

	@GetMapping("/showdusersteam")
	public String showTeams(Principal principal, User user, Model model) {

		user = userService.findByUsername(principal.getName());
		System.out.println(user.getId());
		model.addAttribute("team", teamService.findAllByUsers(user));

		return "viewteams_devloper";
	}
	
	@GetMapping("team/viewteams/{id}")
	public String devloperViewTeamUser(@PathVariable Long id, Team team, Model model) {
		model.addAttribute("team", teamService.getTeamById(id));
		model.addAttribute("user", userService.findAllByTeams(team));
		return "devloper_view_teamuser.html";
	}

}
