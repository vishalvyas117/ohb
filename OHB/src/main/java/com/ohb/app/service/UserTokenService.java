package com.ohb.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohb.app.model.UserToken;
import com.ohb.app.repo.UserTokenRepository;

@Service
public class UserTokenService {

	@Autowired
    private UserTokenRepository userTokenRepository;
	
	public UserToken save(UserToken userToken) {
        return userTokenRepository.save(userToken);
    }

    public UserToken getTokenById(String token) {
        return userTokenRepository.findOne(token);
    }

    public void invalidateToken(UserToken userToken) {
        userTokenRepository.delete(userToken);
    }
}
