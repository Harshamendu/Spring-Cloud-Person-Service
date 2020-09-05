package com.harsha.person.service;

import java.util.List;

import com.harsha.person.entity.Person;

public interface PersonService {

	List<Person> findAllPersons();

	Person savePerson(Person person);

	Person getPersonById(Long personId);

	void removePerson(Long personId, Integer id);

}
