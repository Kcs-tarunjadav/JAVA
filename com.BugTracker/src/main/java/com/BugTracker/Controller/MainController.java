package com.BugTracker.Controller;

import java.security.Principal;

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

import com.BugTracker.Entity.User;
import com.BugTracker.Entity.UserRole;

import com.BugTracker.Service.UserService;


/**
 * 
 * @author Tarun.Jadav
 *Main Controller
 */

@Controller

public class MainController {

	@Autowired
	private UserService userService;

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
	public String adding(User user, @RequestParam("ROLES") Long role, UserRole userRole) {

		userRole.setId(role);

		user.setRole(userRole);
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

		userService.deleteUserById(id);

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
		existingUser.setPassword(user.getPassword());

		userService.saveUser(existingUser);

		return "redirect:/?successupdate";
	}

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

}
