package com.taller.asb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.taller.asb.model.User;

public interface UserRepository extends CustomRepository<User, Long> {
	
	public List<User> findByNameContainingOrLastNameContaining(String name, String lastName);
	public Page<User> findByNameContainingOrLastNameContaining(String name, String lastName, Pageable page);
	
	public User findByIdUser(Long idUser);
	public User findByUsername(String username);
	public User findByDocument(String document);
}
