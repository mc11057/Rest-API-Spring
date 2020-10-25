package com.applaudo.restapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.applaudo.restapi.model.User;
import com.applaudo.restapi.repository.IApplicationUserRepository;
import com.applaudo.restapi.service.IUserApplicationService;

@Service
public class UserApplicationServiceImpl implements IUserApplicationService {

	private IApplicationUserRepository repository;

	@Autowired
	public UserApplicationServiceImpl(IApplicationUserRepository repository) {
		this.repository = repository;

	}

	@Override
	public User findByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public User save(User user) {
		return repository.save(user);
	}

}
