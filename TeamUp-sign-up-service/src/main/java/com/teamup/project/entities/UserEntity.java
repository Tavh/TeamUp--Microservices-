package com.teamup.project.entities;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table (name="users")
public class UserEntity {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long id;

	@Column (name="nickname", nullable=false)
	private String nickname;

	@Column (name="hashed_password", nullable=false)
	private String password;

	@Column (name="email", nullable=false, unique=true)
	private String email;

	@Transient
	private String loginStatus;

	public UserEntity() {
	}
	
	public UserEntity(String nickname, String password, String email,
			String loginStatus) {
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.loginStatus = loginStatus;
	}
	
	public UserEntity(long id, String nickname, String password, String email,
			String loginStatus) {
		this.id = id;
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.loginStatus = loginStatus;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", nickname=" + nickname + ", password=" + password
				+ ", email=" + email + ", loginStatus=" + loginStatus + "]";
	}
}
