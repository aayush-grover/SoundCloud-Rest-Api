package com.upgrad.musichoster.service.business;

import com.upgrad.musichoster.service.entity.UserAuthTokenEntity;
import com.upgrad.musichoster.service.exception.AuthenticationFailedException;

public interface AuthenticationService {

	UserAuthTokenEntity authenticate(final String username, final String password) throws AuthenticationFailedException;
}
