package com.ohb.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ohb.app.model.UserToken;

public interface UserTokenRepository extends JpaRepository<UserToken, String>, JpaSpecificationExecutor<UserToken> {

}
