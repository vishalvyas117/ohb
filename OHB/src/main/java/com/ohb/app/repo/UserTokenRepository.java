package com.ohb.app.repo;

import org.springframework.data.repository.CrudRepository;

import com.ohb.app.model.UserToken;

public interface UserTokenRepository extends CrudRepository<UserToken, String> {

}
