package org.cibertec.edu.pe.repository;

import java.util.List;

import org.cibertec.edu.pe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(String username);
	public List<User> findByUsernameContainingIgnoreCase(String username);
}
