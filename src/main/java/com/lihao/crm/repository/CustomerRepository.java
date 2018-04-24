package com.lihao.crm.repository;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.lihao.crm.entity.Customer;
import com.lihao.crm.entity.SysUser;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
	
	public List<Customer> findAllByIsDeleteNotAndUser(boolean flag, SysUser me);
}
