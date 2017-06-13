package com.ohb.app.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ohb.app.model.User;
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String>{
	@Query("SELECT u FROM User u WHERE u.email = ?2 AND u.username = ?1")
	User findOneByUsername(String userName,String email);
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	User findoneByEmail(String email);
}
