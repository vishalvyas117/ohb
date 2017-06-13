package com.ohb.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohb.app.model.User;
import com.ohb.app.model.UserToken;
import com.ohb.app.repo.UserRepository;
import com.ohb.app.repo.UserTokenRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserTokenRepository userTokenRepository;

	public User getUserByEmail(String email, String username) {
		return userRepository.findOneByUsername(username, email);
	}
	public User getUserByEmail(String email) {
		return userRepository.findoneByEmail(email);
	}

	public User save(User users) {
		return userRepository.save(users);
	}

	public User getUserById(String userId) {
		return userRepository.findOne(userId);
	}

	public User getUserByActivationCode(String token) {
		UserToken userToken = userTokenRepository.findOne(token);

		if (userToken != null) {
			return userRepository.findOne(userToken.getUserId());
		} else {
			return null;
		}
	}
}
