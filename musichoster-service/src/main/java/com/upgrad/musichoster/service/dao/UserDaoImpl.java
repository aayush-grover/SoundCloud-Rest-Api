package com.upgrad.musichoster.service.dao;

import com.upgrad.musichoster.service.entity.UserAuthTokenEntity;
import com.upgrad.musichoster.service.entity.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@PersistenceContext private EntityManager entityManager;

	@Override public UserEntity createUser(UserEntity userEntity) {
		entityManager.persist(userEntity);
		return userEntity;
	}

	@Override public UserEntity getUserByEmail(String email) {
		try {
			return entityManager.createNamedQuery("userByEmail", UserEntity.class).setParameter("email", email)
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override public UserAuthTokenEntity createAuthToken(UserAuthTokenEntity userAuthTokenEntity) {
		entityManager.persist(userAuthTokenEntity);
		return userAuthTokenEntity;
	}

	@Override public void updateUser(UserEntity updatedUserEntity) {
		entityManager.merge(updatedUserEntity);
	}
}
