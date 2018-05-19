package com.lihao.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lihao.crm.entity.Customer;
import com.lihao.crm.entity.SysUser;
import com.lihao.crm.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;


	public List<Customer> loadMine(SysUser me) {
		return (List<Customer>) customerRepository.findAllByIsDeleteNotAndUser(true, me);
	}
	
	public void save(Customer customer) {		
		customerRepository.save(customer);
	}
}
