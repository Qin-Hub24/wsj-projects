package com.app.raghu.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="app_user_tab")
@Data
public class User {
	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@Column(name = "username", unique = true, nullable = false)
	private String username;

	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
}
