package com.course.project.firstProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.course.project.firstProject.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
