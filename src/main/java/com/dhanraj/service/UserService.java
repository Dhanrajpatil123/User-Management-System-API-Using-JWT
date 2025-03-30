package com.dhanraj.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.dhanraj.model.User;

@Service
public class UserService {

	
	private List<User> store = new ArrayList<>();
	
	
	public UserService() {
        store.add(new User(UUID.randomUUID().toString(), "Dhanraj", "dhanraj@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "Raj", "raaj@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "Kaushal", "kaushal@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "Aniket", "aniket@gmail.com"));
    }
	
	
	public List<User> getUser(){
		return this.store;
	}

	
	
}
