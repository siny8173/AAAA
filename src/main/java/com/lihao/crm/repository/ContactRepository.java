package com.lihao.crm.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.lihao.crm.entity.Contact;

public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {

}
