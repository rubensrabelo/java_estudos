package com.course.project.firstProject.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.course.project.firstProject.data.vo.v2.PersonVOV2;
import com.course.project.firstProject.models.Person;

@Service
public class PersonMapper {
	
	public PersonVOV2 convertEntityToVO(Person person) {
		PersonVOV2 vo = new PersonVOV2();
		
		vo.setId(person.getId());
		vo.setFirstname(person.getFirstname());
		vo.setLastname(person.getLastname());
		vo.setAddress(person.getAddress());
		vo.setBithDate(new Date());
		
		return vo;
	}
	
	public Person convertVOToEntity(PersonVOV2 person) {
		Person entity = new Person();
		
		entity.setId(person.getId());
		entity.setFirstname(person.getFirstname());
		entity.setLastname(person.getLastname());
		entity.setAddress(person.getAddress());
		// vo.setBithDate(new Date());
		
		return entity;
	}
}
