package com.taller.asb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.taller.asb.model.User;

public interface UserRepository extends CustomRepository<User, Long> {
	
	public List<User> findByUsernameContaining(String username);
	public Page<User> findByUsernameContaining(String username, Pageable page);
	
	public User findByIdUser(Long idUser);
	public User findByUsername(String username);
	
	
//	@Modifying
//	@Query(value = "UPDATE users u SET u.User_Id001Status = ?2 WHERE User_Id = ?1", nativeQuery = true)
//	public User updateUserStatus(Long idUser, Integer idStatus);
}
