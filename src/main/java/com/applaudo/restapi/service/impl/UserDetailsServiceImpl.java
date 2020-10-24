package com.applaudo.restapi.service.impl;

import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.applaudo.restapi.repository.IApplicationUserRepository;

import static java.util.Collections.emptyList;

/*This class is used by Spring Security
 * **/

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private IApplicationUserRepository applicationUserRepository;

	public UserDetailsServiceImpl(IApplicationUserRepository applicationUserRepository) {
		this.applicationUserRepository = applicationUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.applaudo.restapi.model.User applicationUser = applicationUserRepository.findByUsername(username);
		if (applicationUser == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
	}
	
	public com.applaudo.restapi.model.User findByUserName(String username) {
		return applicationUserRepository.findByUsername(username);
	}
	
}