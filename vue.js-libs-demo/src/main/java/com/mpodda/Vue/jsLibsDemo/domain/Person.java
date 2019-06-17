package com.mpodda.Vue.jsLibsDemo.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String gender;
	private Boolean active;
	private String comments;
	
    @ManyToOne (fetch = FetchType.EAGER)
	private Nationality nationality;
	
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="persons_skills",
            joinColumns=
            @JoinColumn(name="person_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="skill_id", referencedColumnName="id")
    )
    private Set<Skill> skills;
    
	public Person() {
		
	}

	public Person(Integer id, String name, String gender, Boolean active, String comments) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.active = active;
		this.comments = comments;
	}
	
	public Person(Integer id, String name, String gender, Boolean active, String comments, Nationality nationality) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.active = active;
		this.comments = comments;
		this.nationality = nationality;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Nationality getNationality() {
		return nationality;
	}

	public void setNationality(Nationality nationality) {
		this.nationality = nationality;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		
		return String.format("id=%s  name=%s  gender=%s  active=%s  comments=%s  [nationality=%s]", id, name, gender, active, comments, nationality);
	}
}
