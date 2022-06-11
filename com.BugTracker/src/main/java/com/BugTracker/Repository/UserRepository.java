package com.BugTracker.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BugTracker.entity.Team;
import com.BugTracker.entity.User;
import com.BugTracker.entity.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
	List<User> findByFirstname(String firstname);
	
	List<User> findByLastname(String lastname);
	
	List<User> 	findAllByRole(UserRole role);
	
	List<User> findAllByTeams(Team teams);
	
	

	
	
}
        