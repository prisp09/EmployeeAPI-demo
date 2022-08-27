package com.priyanshu.api.rest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.priyanshu.api.rest.Models.User;
import com.priyanshu.api.rest.Repo.UserRepo;

@RestController
public class ApiControllers {
	
	@Autowired
	private UserRepo userRepo;

	@GetMapping(value = "/")
	public String getPage() {
		return "Welcome to my API, this API is completely cloud based.\n\n"
				+ "Use the following HTTP requests to work with the API:\n"
				+ "GET /users: will return all the users currently in the system.\n\n"
				+ "POST /save: will save the user object \n"
				+ "{\"firstName\": String, \"lastName\": String, \"age\": int, \"occupation\": String}\n"
				+ "into the sytem and will automatically assign it a \"id\": int.\n"
				+ "This request will only work if the above object is provided!\n\n"
				+ "PUT /update/{id}: will update the user at the given \"id\" with a new user\n"
				+ "{\"firstName\": String, \"lastName\": String, \"age\": int, \"occupation\": String}\n"
				+ "This request will only work if the above object is provided!\n\n"
				+ "DELETE /delete/{id}: will delete the user present at the provided \"id\" if it exists.";
	}
	
	@GetMapping(value = "/users")
	public List<User> getUsers() {
		return userRepo.findAll();
	}
	
	@PostMapping(value = "/save")
	public String saveUser(@RequestBody User user) {
		userRepo.save(user);
		return "User saved!";
	}
	
	@PutMapping(value = "/update/{id}")
	public String updateUser(@PathVariable long id, @RequestBody User user) {
		User updatedUser = userRepo.findById(id).get();
		updatedUser.setFirstName(user.getFirstName());
		updatedUser.setLastName(user.getLastName());
		updatedUser.setOccupation(user.getOccupation());
		updatedUser.setAge(user.getAge());
		userRepo.save(updatedUser);
		return "User updated!";
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public String deleteUser(@PathVariable long id) {
		
		User deleteUser = userRepo.findById(id).get();
		userRepo.delete(deleteUser);
		
		return "User with id: " + id + ", successfully deleted!";
	}
	
}
