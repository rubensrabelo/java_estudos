package com.system.management.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.management.customer.data.vo.CustomerVO;
import com.system.management.customer.mapper.DozerMapper;
import com.system.management.customer.repository.CustomerRepository;
import com.system.management.customer.service.exceptions.ResourceNotFoundException;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repository;
	
	public List<CustomerVO> findAll() {
		var listVO = DozerMapper.parseListObjects(repository.findAll(), CustomerVO.class);
		
		return listVO;
	}
	
	public CustomerVO findById(Long id) {
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		
		var vo = DozerMapper.parseObject(entity, CustomerVO.class);
		
		return vo;
	}
}
