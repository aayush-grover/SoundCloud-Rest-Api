package com.upgrad.musichoster.service.business;

import com.upgrad.musichoster.service.entity.MusicEntity;
import com.upgrad.musichoster.service.exception.MusicNotFoundException;
import com.upgrad.musichoster.service.exception.UnauthorizedException;
import com.upgrad.musichoster.service.exception.UserNotSignedInException;

public interface AdminService {

	MusicEntity getMusic(final String musicUuid, final String authorization) throws MusicNotFoundException,
			UnauthorizedException, UserNotSignedInException;

	MusicEntity updateMusic(final MusicEntity musicEntity, final String authorization) throws MusicNotFoundException, UnauthorizedException, UserNotSignedInException;

}
