package com.lihao.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lihao.crm.entity.Department;
import com.lihao.crm.entity.Event;
import com.lihao.crm.entity.Project;
import com.lihao.crm.entity.SysUser;
import com.lihao.crm.repository.EventRepository;

@Service
public class EventService {

	@Autowired
	EventRepository eventRepository;
	
	public Event findById(long id) {
		Event event = new Event();
		event.setId(id);
		Example<Event> example = Example.of(event);
		Optional<Event> temp = eventRepository.findOne(example);
		return temp.get();
	}

	public List<Event> loadMine(SysUser me) {
		return (List<Event>) eventRepository.findAllByUserAndIsDeleteNot(me, true);
	}
	
	public List<Event> loadMineByDepartment(SysUser me, Department department) {
		return (List<Event>) eventRepository.findAllByIsDeleteNotAndUserAndDepartment(true, me, department);
	}

	public void save(Event event) {
		eventRepository.save(event);
	}
}
