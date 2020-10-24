package com.applaudo.restapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "role")
public class Role implements Serializable {

	private static final long serialVersionUID = 8743481670047978982L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_seq_gen")
	@SequenceGenerator(name = "roles_seq_gen", sequenceName = "roles_id_seq",allocationSize = 1)
	private long id;
	
	@Enumerated(EnumType.STRING)
	private RoleEnum name;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("roles") 
	private List<User> users;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RoleEnum getName() {
		return name;
	}

	public void setName(RoleEnum name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}



}
