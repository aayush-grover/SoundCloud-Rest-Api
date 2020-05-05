package com.upgrad.musichoster.service.dao;

import com.upgrad.musichoster.service.entity.UserAuthTokenEntity;
import com.upgrad.musichoster.service.entity.UserEntity;


public interface UserDao {

	 UserEntity createUser(UserEntity userEntity);

	 UserEntity getUserByEmail(final String email);

	 UserAuthTokenEntity createAuthToken(final UserAuthTokenEntity userAuthTokenEntity);

	 void updateUser(final UserEntity updatedUserEntity);
}
