package com.claim.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.claim.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

	@Query("SELECT u FROM User u WHERE u.email = ?1 AND u.password = ?2")
	User loginUser(String email, String password);
	
	@Query("SELECT u FROM User u WHERE u.email =?1")
	User findUserByEmail(String email);
	
//	@Transactional
//	@Modifying
//	@Query("UPDATE User u SET u.favorites=?1 where u.id=?2")
//	void addUserFavorite(User tempUser, String currentUser);
}
