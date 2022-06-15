package com.BugTracker.controller;

import java.security.Principal;

import java.util.List;

import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.BugTracker.entity.Team;
import com.BugTracker.entity.User;
import com.BugTracker.entity.UserRole;
import com.BugTracker.service.TeamService;
import com.BugTracker.service.UserService;

/**
 * 
 * @author Tarun.Jadav Main Controller
 */

@Controller

public class MainController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TeamService teamService;

	@GetMapping("/")
	public String homeShow(Principal principal, User user, Model model) {

		user = userService.findByUsername(principal.getName());
		String role = user.getRole().toString();

		if (role.contains("ROLE_ADMIN")) {

			System.out.println("admin is here");
			return "home";
		}

		if (role.contains("ROLE_TESTER")) {

			System.out.println("tester is here");
			return "testerhome";
		}
		if (role.contains("ROLE_DEVLOPER")) {

			System.out.println("devloper is here");
			return "devloperhome";
		}

		return "redirect:/login";

	}

	@RequestMapping("/login")
	public String loginShow() {

		return "login";
	}

	@RequestMapping("/logout-success")
	public String logoutShow() {

		return "logout";
	}

	@GetMapping("/register")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String registerShow(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String adding(User user, @RequestParam("ROLES") Long role, UserRole userRole) throws MessagingException {

		userRole.setId(role);
		user.setRole(userRole);

		userService.emailUser(user);
		userService.saveUser(user);

		return "redirect:/?success";
	}

	@GetMapping("/showuser")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")

	public String showUsers(Model model) {
		model.addAttribute("user", userService.getAllUsers());

		return "viewusers";
	}

	@GetMapping("/user/delete/{id}")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")

	public String deleteUser(@PathVariable Long id) {

		
		User user=userService.getUserById(id);
		List<Team>teams=teamService.findAllByUsers(user);
		for (int i = 0; i < teams.size(); i++) {
			Team team=teams.get(i);
			team.getUsers().remove(user);
			user.getTeams().remove(team);


			teamService.saveTeam(team);
			userService.saveUser(user);
			
		}
	
		userService.deleteUserById(user.getId());

		return "redirect:/showuser";

	}

	@GetMapping("/searchuser")
	public String searchUser(@RequestParam("firstname") String firstname, Model model) {

		model.addAttribute("user", userService.findByFirstname(firstname));

		return "viewusers";
	}

	@GetMapping("/user/update/{id}")
	public String updateUser(@PathVariable Long id, Model model) {

		model.addAttribute("user", userService.getUserById(id));

		return "updateuser";
	}

	@PostMapping("/updateuser")

	public String updateUser(User user, @RequestParam("ROLES") Long role, UserRole userRole) {

		User existingUser = userService.getUserById(user.getId());

		existingUser.setId(existingUser.getId());
		existingUser.setFirstname(user.getFirstname());
		existingUser.setLastname(user.getLastname());
		userRole.setId(role);
		user.setRole(userRole);
		existingUser.setUsername(user.getUsername());
		existingUser.setRole(user.getRole());
		existingUser.setPassword(existingUser.getPassword());

		System.out.println(existingUser.getPassword());

		userService.saveUser(existingUser);

		return "redirect:/?successupdate";
	}

	
	//user profile
	@GetMapping("profile")
	public String userProfile(Principal principal, Model model) {

		model.addAttribute("user", userService.findByUsername(principal.getName()));
		return "updateuser";
	}

	@GetMapping("/searchuserrole")
	public String searchByRole(@RequestParam("ROLES") Long role, Model model, User user, UserRole userRole) {

		userRole.setId(role);

		model.addAttribute("user", userService.findAllByRole(userRole));

		return "viewusers";
	}

	
	//user view team starts here
	@GetMapping("/showdusersteam")
	public String showTeams(Principal principal, User user, Model model) {

		user = userService.findByUsername(principal.getName());
		System.out.println(user.getId());
		model.addAttribute("team", teamService.findAllByUsers(user));

		return "viewteams_devloper";
	}

	@GetMapping("team/viewteams/{id}")
	public String userViewTeamUser(@PathVariable Long id, Team team, Model model) {
		model.addAttribute("team", teamService.getTeamById(id));
		model.addAttribute("user", userService.findAllByTeams(team));
		return "devloper_view_teamuser.html";
	}

}
