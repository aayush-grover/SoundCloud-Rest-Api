package com.upgrad.musichoster.service.dao;

import com.upgrad.musichoster.service.entity.MusicEntity;
import com.upgrad.musichoster.service.entity.UserAuthTokenEntity;

public interface MusicDao {

	MusicEntity createMusic(MusicEntity musicEntity);

	UserAuthTokenEntity getUserAuthToken(final String accesstoken);

	MusicEntity getMusic(final String musicUuid);

	MusicEntity getMusicById(final long Id);

	MusicEntity updateMusic(final MusicEntity musicEntity);
}
