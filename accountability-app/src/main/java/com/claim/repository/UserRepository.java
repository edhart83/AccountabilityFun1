package com.claim.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.claim.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
//public interface UserRepository extends JpaRepository<User, String> {

	@Query("SELECT u FROM User u WHERE u.email = ?1 AND u.password = ?2")
	User loginUser(String email, String password);
	
	@Query("SELECT u FROM User u WHERE u.email =?1")
	User findUserByEmail(String email);
	
//	@Transactional
//	@Modifying
//	@Query("UPDATE User u SET u.favorites=?1 where u.email=?2")
//	void addUserFavorite(String string, String currentUser);
}
