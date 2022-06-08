package com.BugTracker.Service;


import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.BugTracker.Entity.User;
import com.BugTracker.Entity.UserRole;

public interface UserService  extends UserDetailsService{

	
	User saveUser(User user);
	
	User findByUsername(String username);
	
	List<User> getAllUsers();
	
	User getUserById(Long id);
	
	void deleteUserById(Long id);
	
	List<User> findByFirstname(String firstname);
	
	List<User> 	findAllByRole(UserRole role);

	
	
	
	
	
	
	
	
}
