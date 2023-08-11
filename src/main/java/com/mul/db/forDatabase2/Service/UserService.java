package com.mul.db.forDatabase2.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mul.db.forDatabase2.enties.User;
import com.mul.db.forDatabase2.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	public UserRepository repository;
	
	
	// save single user
		public User saveUser(User us) {
			return repository.save(us);
		}

		// save multiple user
		public List<User> saveProducts(List<User> users) {
			return repository.saveAll(users);
		}

		// get multiple User
		public List<User> getUsers() {
			return repository.findAll();
		}

		// get single User by its id
		public User getUserById(int id) {
			return repository.findById(id).orElse(null);
		}

		// delete single User
		public String deleteUser(int  id) {
			repository.deleteById(id);
			return "product removed !! " +id;
		}

		// update single product
		public User updateUser(User user) {
			User existinguser = repository.findById(user.getId()).orElse(null);
			existinguser.setFirstname(user.getFirstname());
			existinguser.setLastname(user.getLastname());
			return repository.save(existinguser);
		}

}
