package com.techlab.company.service;
import java.util.List;

import com.techlab.company.entity.User;

public interface UserSerive {

	
	User addUser(User user);
	User getUserById(int userId);
	
	User assignRoleToUser(int userId,List<Integer> roleIds);
}
