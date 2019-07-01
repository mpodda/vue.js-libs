package com.mpodda.Vue.jsLibsDemo.ui.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mpodda.Vue.jsLibsDemo.domain.Nationality;
import com.mpodda.Vue.jsLibsDemo.domain.Person;
import com.mpodda.Vue.jsLibsDemo.domain.Skill;
import com.mpodda.Vue.jsLibsDemo.repository.NationalityRepository;
import com.mpodda.Vue.jsLibsDemo.repository.PersonRepository;
import com.mpodda.Vue.jsLibsDemo.repository.SkillRepository;

@Controller
public class PersonsController {
	private PersonRepository personRepository;
	private NationalityRepository nationalityRepository;
	private SkillRepository skillRepository;
	
	@Autowired
	public PersonsController (PersonRepository personRepository, NationalityRepository nationalityRepository, SkillRepository skillRepository) {
		this.personRepository = personRepository;
		this.nationalityRepository = nationalityRepository;
		this.skillRepository = skillRepository;
	}

	@RequestMapping("/")
	public String home() {
		return "persons/home";
	}

	@RequestMapping("/dialogVue")
	public String dialogVue() {
		return "persons/dialog.vue";
	}
	
	@RequestMapping("/simpleList")
	public String simpleList() {
		return "persons/simpleList";
	}
	
	
	@RequestMapping("/withRendererList")
	public String withRendererList() {
		return "persons/withRendererList";
	}
	
	@RequestMapping("/sortableList")
	public String sortableList() {
		return "persons/sortableList";
	}
	
	@RequestMapping("/pagingList")
	public String pagingList() {
		return "persons/pagingList";
	}
	
	@RequestMapping("/simpleManageList")
	public String simpleManageList() {
		return "persons/simpleManageList";
	}
	
	@RequestMapping("/multipleGrids")
	public String multipleGrids() {
		return "persons/multipleGrids";
	}

	
	@RequestMapping("/persons")
	public @ResponseBody Iterable<Person> loadPersons(){
		return this.personRepository.findAll();
	}
	
	@RequestMapping("/skills")
	public @ResponseBody Iterable<Skill> loadSkills(){
		return this.skillRepository.findAll();
	}
	
	
	@RequestMapping("/nationalities")
	public @ResponseBody Iterable<Nationality> loadNationalities(){
		return this.nationalityRepository.findAll();
	}
	
	@RequestMapping(value = "/person/save", method = RequestMethod.POST)
	public @ResponseBody Person savePerson(@RequestBody Person person) {
		return this.personRepository.save(person);
	}	
	
	@RequestMapping(value = "/person/delete", method = RequestMethod.POST)
	public @ResponseBody void deletePerson(@RequestBody Person person) {
		this.personRepository.deleteById(person.getId());
	}	
	
	@RequestMapping(value = "/person/create", method = RequestMethod.POST)
	public @ResponseBody Person createPerson() {
		Person person = new Person();
		person.setActive(Boolean.TRUE);
		
		return person;
	}
	
}
