package com.applaudo.restapi.service;

import com.applaudo.restapi.model.User;

public interface IUserApplicationService {

	User findByUsername(String username);

	User save(User user);
}
