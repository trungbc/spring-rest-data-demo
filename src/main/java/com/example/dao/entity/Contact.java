package com.example.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The persistent class for the contact database table.
 * 
 */
@Entity
@NamedQuery(name="Contact.findAll", query="SELECT c FROM Contact c")
public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private Timestamp birthday;

	private String company;

	@Email
	@NotEmpty
	@NotNull
	private String email;

	private String name;

	@JsonProperty(value="nick_name")
	private String nick;

	private String note;

	private String phone;

	private String title;

	@Column(name="user_id")
	@JsonProperty(value="user_id")
	private int userId;

	public Contact() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNick() {
		return this.nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}