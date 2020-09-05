package com.harsha.person.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harsha.person.entity.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {

	Optional<Person> findByPersonId(Long id);

	void deleteByPersonId(Long personId);

}
