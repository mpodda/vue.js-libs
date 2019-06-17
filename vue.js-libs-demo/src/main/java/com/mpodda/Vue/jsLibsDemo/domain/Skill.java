package com.mpodda.Vue.jsLibsDemo.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Skill implements Serializable {
	private static final long serialVersionUID = -5560544485581957299L;
	
	@Id
	private Integer id;

	private String description; 
	
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="persons_skills",
            joinColumns=
            	@JoinColumn(name="skill_id", referencedColumnName="id"),
            inverseJoinColumns=
            	@JoinColumn(name="person_id", referencedColumnName="id")
            
    )
    @JsonBackReference
	private Set<Person> persons;

    
    public Skill() {
		
	}


	public Skill(Integer id, String description) {
		this.id = id;
		this.description = description;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Set<Person> getPersons() {
		return persons;
	}


	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}
}
