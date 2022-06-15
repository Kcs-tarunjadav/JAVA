package com.BugTracker.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.BugTracker.entity.Team;
import com.BugTracker.entity.User;
import com.BugTracker.service.TeamService;
import com.BugTracker.service.UserService;

@Controller
public class TeamController {

	@Autowired
	private TeamService teamService;

	@Autowired
	private UserService userService;

	@GetMapping("/addteam")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")

	public String showTeamReg() {

		return "team";
	}

	@PostMapping("/addteam")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")

	public String addTeam(Team team) {

		teamService.saveTeam(team);
		return "redirect:/?team";
	}

	@GetMapping("/showteam")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")

	public String showTeams(Model model) {

		model.addAttribute("team", teamService.getAllTeams());

		return "showteam";

	}

	@GetMapping("team/adduser/{id}")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")

	public String teamAddUser(Model model, @PathVariable Long id, Team team) {

		team = teamService.getTeamById(id);

		List<User> users = userService.getAllUsers();
		List<User> teamUser = userService.findAllByTeams(team);
		
		System.out.println(users.size());
		
		System.out.println(teamUser.size());
		
	
		for (int i = 0; i <teamUser.size(); i++) {
			User teamuser2=teamUser.get(i);
			users.remove(teamuser2);
			
			
		}
		
		/*
		 * System.out.println(users.size());
		 * 
		 * System.out.println(teamUser.size()); for (int i = 1; i <users.size(); i++) {
		 * 
		 * for (int j = 1; j <teamUser.size(); j++) { User user = users.get(i); User
		 * user2 = teamUser.get(j);
		 * 
		 * if (user.getId() == user2.getId()) {
		 * 
		 * System.out.println(user); users.remove(user);
		 * 
		 * } } }
		 */

		model.addAttribute("team", teamService.getTeamById(id));
		model.addAttribute("user", users);

		return "addteams_user";
	}

	@RequestMapping("team/adduser/user/addteam/{id}/{teamid}")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")

	public String addTeamMember(@PathVariable("id") Long id, @PathVariable("teamid") Long teamid, Team team,
			User user) {

		team = teamService.getTeamById(teamid);
		user = userService.getUserById(id);

		team.getUsers().add(user);
		user.getTeams().add(team);

		teamService.saveTeam(team);
		userService.saveUser(user);

		return "redirect:/showteam?added";
	}

	@RequestMapping("team/viewteam/user/remove/{id}/{teamid}")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")

	public String removeTeamMember(@PathVariable("id") Long id, @PathVariable("teamid") Long teamid, Team team,
			User user) {

		team = teamService.getTeamById(teamid);
		user = userService.getUserById(id);

		team.getUsers().remove(user);
		user.getTeams().remove(team);

		teamService.saveTeam(team);
		userService.saveUser(user);

		return "redirect:/showteam?remove";
	}

	@GetMapping("/team/delete/{id}")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")

	public String deleteTeam(@PathVariable Long id) {

		teamService.deleteTeamById(id);

		return "redirect:/showteam?delete";
	}

	@GetMapping("/team/viewteam/{id}")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")

	public String viewTeamMembers(@PathVariable Long id, Team team, Model model) {

		
		System.out.println(id);
		team = teamService.getTeamById(id);

		model.addAttribute("team", teamService.getTeamById(id));
		model.addAttribute("user", userService.findAllByTeams(team));

		return "view_teamusers";
	}
	
	

}
