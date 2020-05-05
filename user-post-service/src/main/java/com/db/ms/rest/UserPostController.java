package com.db.ms.rest;

import com.db.ms.entity.Employee;
import com.db.ms.entity.Post;
import com.db.ms.exception.UserNotFoundException;
import com.db.ms.repo.PostRepository;
import com.db.ms.repo.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserPostController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@GetMapping("/")
	public Map<String, String> defaultPage() {
		Map<String, String> data = new HashMap<>();
		data.put("message", "user-post-service v1");
		log.info("defaultPage -> {}", data);
		return data;
	}

	@GetMapping("/users")
	public List<Employee> retrieveAllUsers() {
		List<Employee> users = userRepository.findAll();
		log.info("retrieveAllUsers -> {}", users);
		return users;
	}

	@GetMapping("/users2/{id}")
	public EntityModel<Employee> retrieveUser2(@PathVariable int id) {
		System.out.print("test");
		Optional<Employee> user = userRepository.findById(id);

		if (!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}

		EntityModel<Employee> model = new EntityModel<>(user.get());
		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkTo.withRel("all-users"));

		return model;
	}

	@GetMapping("/users/{id}")
	public Employee retrieveUser(@PathVariable int id) {
		Optional<Employee> user = userRepository.findById(id);

		if (!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		return user.get();
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}

	//
	// input - details of user
	// output - CREATED & Return the created URI

	// HATEOAS

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody Employee user) {
		Employee savedUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}
	
	@GetMapping("/users/{id}/posts")
	public List<Post> retrieveAllUsers(@PathVariable int id) {
		Optional<Employee> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		
		return userOptional.get().getPosts();
	}


	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
		
		Optional<Employee> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}

		Employee emp = userOptional.get();
		
		post.setEmployee(emp);
		
		postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}
}
