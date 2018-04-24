package com.lihao.crm.web.transform;

import com.lihao.crm.entity.Contact;
import com.lihao.crm.entity.Customer;
import com.lihao.crm.web.object.CustomerDto;

public class CustomerTransform {

	public static CustomerDto Customer2Dto(Customer customer) {

		CustomerDto dto = new CustomerDto();

		dto.customerId = customer.getId();
		dto.postcode = customer.getPostcode();
		dto.city = customer.getCity();
		dto.business = customer.getBusiness();
		dto.registrationTime = customer.getRegistrationTime();
		dto.level = customer.getLevel();
		dto.customertype = customer.getType();
		dto.source = customer.getSource();
		dto.demand = customer.getDemand();
		dto.user = customer.getUser();
		dto.isDelete = customer.getIsDelete();

		Contact contact = customer.getContact();

		dto.contactId = contact.getId();
		dto.name = contact.getName();
		dto.sex = contact.getSex();

		dto.department = contact.getDepartment();
		dto.duty = contact.getDuty();
		dto.type = contact.getType();

		dto.officePhone = contact.getOfficePhone();
		dto.fax = contact.getFax();
		dto.phone = contact.getPhone();
		dto.email = contact.getEmail();
		dto.address = contact.getAddress();
		dto.isPrimary = contact.getIsPrimary();

		return dto;
	}

	public static Customer Dto2SysUser(CustomerDto dto) {
		Customer customer = new Customer();
		
		customer.setId(dto.customerId);
		customer.setBusiness(dto.getBusiness());
		customer.setCity(dto.city);
		customer.setDemand(dto.demand);
		customer.setIsDelete(dto.isDelete);
		customer.setLevel(dto.level);
		customer.setPostcode(dto.postcode);
		customer.setRegistrationTime(dto.registrationTime);
		customer.setSource(dto.source);
		customer.setType(dto.customertype);
		customer.setUser(dto.user);

		Contact contact = new Contact();
		customer.setContact(contact);

		contact.setId(dto.contactId);
		contact.setName(dto.name);
		contact.setDepartment(dto.department);
		contact.setDuty(dto.duty);
		contact.setEmail(dto.email);
		contact.setFax(dto.fax);
		contact.setIsPrimary(dto.isPrimary);
		contact.setOfficePhone(dto.officePhone);
		contact.setPhone(dto.phone);
		contact.setSex(dto.sex);
		contact.setType(dto.type);
		contact.setAddress(dto.address);
		return customer;
	}
}
