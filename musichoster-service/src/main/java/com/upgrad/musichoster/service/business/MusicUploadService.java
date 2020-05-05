package com.upgrad.musichoster.service.business;

import com.upgrad.musichoster.service.entity.MusicEntity;
import com.upgrad.musichoster.service.exception.UploadFailedException;

public interface MusicUploadService {
	MusicEntity upload(MusicEntity musicEntity, final String authorizationToken) throws UploadFailedException;
}
