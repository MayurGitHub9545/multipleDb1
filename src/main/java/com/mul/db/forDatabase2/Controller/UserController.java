package com.mul.db.forDatabase2.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.mul.db.forDatabase2.Service.UserService;
import com.mul.db.forDatabase2.enties.User;

 
@RestController
public class UserController {

	@Autowired
	public UserService service;
	
	
	@PostMapping("/adduser")
	public User adduser(@RequestBody User con) {
		return service.saveUser(con);
	}

	@PostMapping("/addAllusers")
	public List<User> addUser(@RequestBody List<User> cons) {
		return service.saveProducts(cons);
	}

	@PutMapping("/updateUser")
	public User updateUser(@RequestBody User con) {
		return service.updateUser(con);
	}

/////////////////////////

	@GetMapping("/User")
	public List<User> findAllUser() {
		return service.getUsers();
	}

	@GetMapping("/userbyid/{pid}")
	public User findUserById(@PathVariable int  id) {
		return service.getUserById(id);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		return service.deleteUser(id);
	}
	
}
