package com.sjsu.cmpe202.property.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sjsu.cmpe202.property.repositories.UserRepository;
import com.sjsu.cmpe202.search.retail.listing.model.Property;
import com.sjsu.cmpe202.search.retail.listing.model.User;
import com.sjsu.cmpe202.search.retail.listing.services.UserServices;

@Repository
@RestController
public class UserController {
	private UserServices userServices;
	@Autowired
	EntityManager em;

	@Autowired
	UserRepository userRepository;

	@Autowired
	public void setUserService(UserServices userService) {
		this.userServices = userService;
	}
	
	
	
	/*
	 * Shows all users
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/user/list")
	public List<User> listUsers(Model model){

		ArrayList<User> listOfUser = new ArrayList<User>();

		model.addAttribute("user", userServices.listAll());

		Map<String, Object> modelMap = model.asMap();
		listOfUser  = (ArrayList<User>) modelMap.get("user");
		return listOfUser;
	}

	/*
	 * Shows property by userID
	 */
	@RequestMapping("user/show/{id}")
	public User getUser(@PathVariable String id, Model model){
		model.addAttribute("user", userServices.getById(Long.valueOf(id)));
		ArrayList<User> listOfUser = new ArrayList<User>();        
		Map<String, Object> modelMap = model.asMap();
		listOfUser=(ArrayList<User>) modelMap.get("user");
		return listOfUser.get(0);


	}

	
	
	
	
	/*
	 * Add new users to the table
	 */
	@RequestMapping(method = RequestMethod.POST, value = "user/add")
	public void getProperty(@RequestBody User user)
	{
		System.out.println("user email:"+user.getEmail());

		userServices.saveUser(user);

	}
	/*
	@PostMapping("/user")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getUserid()).toUri();

		return ResponseEntity.created(location).build();

	}
	*/
	
}
