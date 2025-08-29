package org.cibertec.edu.pe.service.impl;

import java.util.List;

import org.cibertec.edu.pe.dto.ResponseDTO;
import org.cibertec.edu.pe.dto.UserRequestDTO;
import org.cibertec.edu.pe.entity.User;
import org.cibertec.edu.pe.repository.UserRepository;
import org.cibertec.edu.pe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(null);
	}
	
	@Override
	public User findUserByName(String username) {
	    return userRepository.findByUsername(username);
	}
	
	@Override
	public ResponseDTO findByUsernameContainingIgnoreCase(String username) {
		// TODO Auto-generated method stub
		ResponseDTO response = new ResponseDTO();
		List<User> listUser = userRepository.findByUsernameContainingIgnoreCase(username);
		
			response.setData(listUser);
			response.setStatusCode(200);
			response.setError(false);
			response.setMessage("List of users matching "+ username);
		return response;
		
	}
	
	@Override
	public ResponseDTO createUser(UserRequestDTO user) {
		ResponseDTO response = new ResponseDTO();
		System.out.println(user.getUsername()+user.getFirstName());
		User dbUser = userRepository.findByUsername(user.getUsername());
		if(dbUser == null) {
			User newUser = new User();
			newUser.setUsername(user.getUsername());
			newUser.setPassword(user.getPassword());
			newUser.setEmail(user.getEmail());
			newUser.setFirstName(user.getFirstName());
			newUser.setLastName(user.getLastName());
			newUser.setPhone(user.getPhone());
			newUser.setImageUrl(user.getImageUrl());
			dbUser = userRepository.save(newUser);
			response.setData(dbUser);
			response.setStatusCode(201);
			response.setError(false);
			response.setMessage("User created succesfully");
		} else {
			response.setStatusCode(400);
			response.setError(true);
			response.setMessage("Username already exists");
		}
		return response;
	}

	@Override
	public ResponseDTO updateUser(Long id, User user) {
	    ResponseDTO response = new ResponseDTO();
	    User existingUser = userRepository.findById(id).orElse(null);
	    if (existingUser != null) {
	        existingUser.setUsername(user.getUsername());
	        existingUser.setPassword(user.getPassword());
	        existingUser.setEmail(user.getEmail());
	        existingUser.setFirstName(user.getFirstName());
	        existingUser.setLastName(user.getLastName());
	        existingUser.setPhone(user.getPhone());
	        existingUser.setImageUrl(user.getImageUrl());
	        
	        userRepository.save(existingUser);
	        
	        response.setData(existingUser);
	        response.setStatusCode(200);
	        response.setError(false);
	        response.setMessage("User updated successfully");
	    } else {
	        response.setStatusCode(404);
	        response.setError(true);
	        response.setMessage("User not found with ID: " + id);
	    }
	    return response;
	}

	@Override
	public ResponseDTO deleteUser(Long id) {
	    ResponseDTO response = new ResponseDTO();
	    User user = userRepository.findById(id).orElse(null);
	    if (user != null) {
	        userRepository.delete(user);
	        
	        response.setStatusCode(200);
	        response.setError(false);
	        response.setMessage("User deleted successfully");
	    } else {
	        response.setStatusCode(404);
	        response.setError(true);
	        response.setMessage("User not found with ID: " + id);
	    }
	    return response;
	}

	@Override
	public ResponseDTO login(String username, String password) {
	    ResponseDTO response = new ResponseDTO();
	    User user = userRepository.findByUsername(username);
	    
	    if (user != null) {
	        if (user.getPassword().equals(password)) {
	            response.setStatusCode(200);
	            response.setError(false);
	            response.setMessage("Login successful");
	            response.setData(user);
	        } else {
	            response.setStatusCode(400);
	            response.setError(true);
	            response.setMessage("Password is incorrect");
	        }
	    } else {
	        response.setStatusCode(400);
	        response.setError(true);
	        response.setMessage("Username doesn't exist");
	    }
	    
	    return response;
	}


}
