package com.lihao.crm;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.lihao.crm.entity.SysRole;
import com.lihao.crm.entity.SysUser;
import com.lihao.crm.repository.SysUserRepository;

@Component
public class StartRunner implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(StartRunner.class);

	@Autowired
	private SysUserRepository sysUserRepository;

	@Override
	public void run(String... args) throws Exception {
		logger.info("message");

//		SysUser sysUser = new SysUser();
//		sysUser.setId(5l);
//		sysUser.setPassword("{noop}admin");
//		sysUser.setUsername("admin");
//		List<SysRole> l = new ArrayList<>();
//		SysRole role = new SysRole();
//		role.setId(1l);
//		l.add(role);
//		sysUser.setRoles(l);
//		
//		Contact contact = new Contact();
//		contact.setName("admin");
//		contactRepository.save(contact);
//		sysUser.setContact(contact);
//		sysUserRepository.save(sysUser);

	}

}
