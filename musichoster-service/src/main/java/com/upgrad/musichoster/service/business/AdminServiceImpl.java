package com.upgrad.musichoster.service.business;

import com.upgrad.musichoster.service.dao.MusicDao;
import com.upgrad.musichoster.service.entity.MusicEntity;
import com.upgrad.musichoster.service.entity.UserAuthTokenEntity;
import com.upgrad.musichoster.service.exception.MusicNotFoundException;
import com.upgrad.musichoster.service.exception.UnauthorizedException;
import com.upgrad.musichoster.service.exception.UserNotSignedInException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service public class AdminServiceImpl implements AdminService {

	@Autowired private MusicDao musicDao;

	@Override public MusicEntity getMusic(final String musicUuid, final String authorization)
			throws MusicNotFoundException, UnauthorizedException, UserNotSignedInException {
		UserAuthTokenEntity userAuthTokenEntity = musicDao.getUserAuthToken(authorization);
		if (userAuthTokenEntity == null) {
			throw new UserNotSignedInException("USR-001", "You are not Signed in, sign in first to get the details of the music");
		}

		String role = userAuthTokenEntity.getUser().getRole();
		if (role.equals("admin")) {
			MusicEntity musicEntity = musicDao.getMusic(musicUuid);
			if (musicEntity == null) {
				throw new MusicNotFoundException("IMG-001", "Music with Uuid not found");
			}
			return musicEntity;
		} else
			throw new UnauthorizedException("ATH-001", "UNAUTHORIZED Access, Entered user is not an admin");
	}

	@Override @Transactional(propagation = Propagation.REQUIRED)
	public MusicEntity updateMusic(final MusicEntity musicEntity, final String authorization)
			throws MusicNotFoundException, UnauthorizedException, UserNotSignedInException {

		UserAuthTokenEntity userAuthTokenEntity = musicDao.getUserAuthToken(authorization);

		if (userAuthTokenEntity == null) {
			throw new UserNotSignedInException("USR-001", "You are not Signed in, sign in first to update the details of the music");
		}

		String role = userAuthTokenEntity.getUser().getRole();
		if (role.equals("admin")) {

			MusicEntity existingmusicEntity = musicDao.getMusicById(musicEntity.getId());

			if (existingmusicEntity == null) {
				throw new MusicNotFoundException("IMG-002", "Music with given id is not found");
			}

			musicEntity.setUuid(existingmusicEntity.getUuid());
			musicEntity.setNo_of_likes(existingmusicEntity.getNo_of_likes());
			musicEntity.setUser(existingmusicEntity.getUser());
			musicEntity.setCreated_at(existingmusicEntity.getCreated_at());

			return musicDao.updateMusic(musicEntity);
		}else {
			throw new UnauthorizedException("ATH-001", "Unauthorized Access, Entered user is not an admin");
		}
	}
}
