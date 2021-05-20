package co.edu.ufps.parcial.modelo;

import java.io.Serializable;
import java.util.Date;

public class Cyclist implements Serializable {
	private Integer id;
	private String name;
	private String email;
	private Date birthdate;
	private Country country;
	private Team team;
	public Cyclist(Integer id, String name, String email, Date birthdate, Country country, Team team) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.birthdate = birthdate;
		this.country = country;
		this.team = team;
	}
	public Cyclist() {
		super();
	}
	public Cyclist(Integer id) {
		super();
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	
	
}
