package com.mpodda.Vue.jsLibsDemo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.mpodda.Vue.jsLibsDemo.domain.Nationality;
import com.mpodda.Vue.jsLibsDemo.domain.Person;
import com.mpodda.Vue.jsLibsDemo.domain.Skill;
import com.mpodda.Vue.jsLibsDemo.repository.NationalityRepository;
import com.mpodda.Vue.jsLibsDemo.repository.PersonRepository;
import com.mpodda.Vue.jsLibsDemo.repository.SkillRepository;

@Component
public class DataInitRunner implements ApplicationRunner {
	private static final Logger logger = LoggerFactory.getLogger(DataInitRunner.class);
	
	private PersonRepository personRepository;
	private NationalityRepository nationalityRepository;
	private SkillRepository skillRepository;
	
	@Autowired
	public DataInitRunner(PersonRepository personRepository, NationalityRepository nationalityRepository, SkillRepository skillRepository) {
		this.personRepository = personRepository;
		this.nationalityRepository = nationalityRepository;
		this.skillRepository = skillRepository;
	}
	
	@Override
	public void run(ApplicationArguments arguments) throws Exception {
        Nationality italian = new Nationality(1, "IT", "Italian");
        Nationality german = new Nationality(2, "DE", "German");
        Nationality greek = new Nationality(3, "GR", "Greek");
        Nationality british = new Nationality(4, "GB", "British");

        this.nationalityRepository.save(italian);
        this.nationalityRepository.save(german);
        this.nationalityRepository.save(greek);
        this.nationalityRepository.save(british);
        
        List<Nationality> nationalities = new ArrayList<>();
        nationalities.add(italian);
        nationalities.add(german);
        nationalities.add(greek);
        nationalities.add(british);
        
        
        logger.info("{} Nationalities have been created", this.nationalityRepository.count());
        
        Person marcello = new Person(1, "Marcello", "M", true, "", italian);
        Person mary = new Person(2, "Mary", "W", true, "Good Girld", greek);
        
        
//        this.personRepository.save(new Person(3, "Jon", "M", false, "Good Boy", british));
//        this.personRepository.save(new Person(4, "James", "M", true, "", british));
//        this.personRepository.save(new Person(5, "Johanna", "W", true, "", german));
//        this.personRepository.save(new Person(6, "George", "M", false, "", british));
//        this.personRepository.save(new Person(7, "Larry", "M", true, "", british));
//        this.personRepository.save(new Person(8, "Hans", "M", true, "", german));
//        this.personRepository.save(new Person(9, "Matteo", "M", true, "", italian));
//        this.personRepository.save(new Person(10, "Anna", "W", true, "", greek));
        
        
        
        
        
        Skill softwareDevelopment = new Skill(1, "Software Development");
        Skill databaseDesign = new Skill(2, "Database Design");
        Skill html5 = new Skill(3, "HTML 5");
        Skill javascript = new Skill(4, "Javascript");
        Skill uml = new Skill(5, "UML");
        Skill oracle = new Skill(6, "Oracle");
        Skill msOffice = new Skill(7, "Ms Office");
        Skill designPatterns = new Skill(8, "Design Patterns");
        Skill sqlServer = new Skill(9, "Sql Server");
        Skill spring = new Skill(10, "Spring");
        Skill jpa = new Skill(11, "JPA");
        
        this.skillRepository.save(softwareDevelopment);
        this.skillRepository.save(databaseDesign);
        this.skillRepository.save(html5);
        this.skillRepository.save(javascript);
        this.skillRepository.save(uml);
        this.skillRepository.save(oracle);
        this.skillRepository.save(msOffice);
        this.skillRepository.save(designPatterns);
        this.skillRepository.save(sqlServer);
        this.skillRepository.save(spring);
        this.skillRepository.save(jpa);
        
        logger.info("{} Skills have been added", this.skillRepository.count());        
        
        List<Skill> marcellosSkills = new ArrayList<>();
        marcellosSkills.add(softwareDevelopment);
        marcellosSkills.add(sqlServer);
        marcellosSkills.add(javascript);

        marcello.setSkills(new HashSet<Skill>(marcellosSkills));
        
        this.personRepository.save(marcello);
        this.personRepository.save(mary);
        
        logger.info("{} Persons have been added", this.personRepository.count());
	} 
	

}
