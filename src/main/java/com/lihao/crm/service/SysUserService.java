package com.lihao.crm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lihao.crm.entity.SysUser;
import com.lihao.crm.repository.ContactRepository;
import com.lihao.crm.repository.SysUserRepository;

@Service
public class SysUserService implements UserDetailsService {

	@Autowired
	SysUserRepository userRepository;

	@Autowired
	ContactRepository contactRepository;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		SysUser user = userRepository.findByUsername(s);
		if (user == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		return user;
	}

	public List<SysUser> findAll() {
		Sort sort = new Sort(Direction.ASC, "id");
		return (List<SysUser>) userRepository.findAll(sort);
	}

	public List<SysUser> findAllTechnicist() {
		Sort sort = new Sort(Direction.ASC, "id");

		List<SysUser> users = (List<SysUser>) userRepository.findAll(sort);

		List<SysUser> technicists = new ArrayList<>();

		users.forEach(u -> {

			u.getRoles().forEach(r -> {
				if (r.getName().equals("ROLE_TECHNICIST"))
					technicists.add(u);
			});
		});

		return technicists;
	}

	public void save(SysUser sysUser) {

		contactRepository.save(sysUser.getContact());

		userRepository.save(sysUser);
	}

	public void delete(SysUser sysUser) {
		userRepository.delete(sysUser);
	}

}
