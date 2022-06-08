package com.BugTracker.Service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.BugTracker.Entity.User;
import com.BugTracker.Entity.UserRole;
import com.BugTracker.Principle.UserPrinciple;
import com.BugTracker.Repository.UserRepository;
import com.BugTracker.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	

	public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);

		if (user == null) {

			throw new UsernameNotFoundException("user 404");
		}

		return new UserPrinciple(user);
	}

	@Override
	public User saveUser(User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {

		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> getAllUsers() {

		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long id) {

		return userRepository.findById(id).get();
	}

	@Override
	public void deleteUserById(Long id) {

		userRepository.deleteById(id);
	}

	@Override
	public List<User> findByFirstname(String firstname) {

		return userRepository.findByFirstname(firstname);
	}

	@Override
	public List<User> findAllByRole(UserRole role) {
		
		return userRepository.findAllByRole(role);
	}


}
