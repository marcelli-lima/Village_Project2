package com.example.projeto.model.transport;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class VillageDTO {

	private Integer id;
    private String name;
    private String surname;
    private Date birthDate;
    private String CPF;
    private BigDecimal income;
    private String email;
    private String password;
    private List<String> roles;
    
    
   
	public VillageDTO(Integer id, String name, String surname, Date birthDate, String cPF, BigDecimal income,
			String email, String password, List<String> roles) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		CPF = cPF;
		this.income = income;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSurname() {
		return surname;
	}



	public void setSurname(String surname) {
		this.surname = surname;
	}



	public Date getBirthDate() {
		return birthDate;
	}



	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}



	public String getCPF() {
		return CPF;
	}



	public void setCPF(String cPF) {
		CPF = cPF;
	}



	public BigDecimal getIncome() {
		return income;
	}



	public void setIncome(BigDecimal income) {
		this.income = income;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public List<String> getRoles() {
		return roles;
	}



	public void setRoles(List<String> roles) {
		this.roles = roles;
	}




}
