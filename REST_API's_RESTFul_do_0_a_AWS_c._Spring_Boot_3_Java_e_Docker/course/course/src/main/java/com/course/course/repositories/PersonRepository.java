package com.course.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.course.course.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
