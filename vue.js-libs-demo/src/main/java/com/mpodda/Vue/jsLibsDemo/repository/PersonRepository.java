package com.mpodda.Vue.jsLibsDemo.repository;

import org.springframework.data.repository.CrudRepository;

import com.mpodda.Vue.jsLibsDemo.domain.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

}
