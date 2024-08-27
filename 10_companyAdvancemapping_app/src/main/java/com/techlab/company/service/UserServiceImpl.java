package com.techlab.company.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlab.company.entity.Role;
import com.techlab.company.entity.User;
import com.techlab.company.repository.RoleRepository;
import com.techlab.company.repository.UserRepository;

@Service
public class UserServiceImpl implements UserSerive{
	
	@Autowired
	private UserRepository userRepository;

	
	@Autowired
	private RoleRepository roleRepository;
	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(userId).orElseThrow( ()-> new NullPointerException("User Not Found"));
	}

	@Override
	public User assignRoleToUser(int userId, List<Integer> roleIds) {
		// TODO Auto-generated method stub
		User dbUser = getUserById(userId);
		
		List<Role> dbUserRoles=dbUser.getRoles();
		
		if(dbUserRoles==null) {
			dbUserRoles=new ArrayList<>();
		}
		
		Set<Role> toAddRole = new HashSet<>();
		
		roleIds.forEach((roleid)->{
			Role dbRole= roleRepository.findById(roleid).orElseThrow(()-> new NullPointerException("Role not fount id: "+roleid));
			List<User> dbRoleUsers = dbRole.getUsers();
			if(dbRoleUsers==null) {
				dbRoleUsers=new ArrayList<>();;
			}
			
			dbRoleUsers.add(dbUser);
			dbRole.setUsers(dbRoleUsers);
			
			toAddRole.add(dbRole);
		});
		
		dbUserRoles.addAll(toAddRole);
		dbUser.setRoles(dbUserRoles);
		
		return userRepository.save(dbUser);
	}

}
