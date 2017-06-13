package com.ohb.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ohb.app.model.User;

@Repository(value = "userRepository")
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
	
	@Query("SELECT u FROM User u WHERE u.email = ?2 AND u.username = ?1")
	User findOneByUsername(String userName, String email);

	@Query("SELECT u FROM User u WHERE u.email = ?1")
	User findoneByEmail(String email);
}
