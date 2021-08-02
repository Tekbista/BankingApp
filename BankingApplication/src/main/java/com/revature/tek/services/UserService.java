package com.revature.tek.services;

import com.revature.tek.models.User;

public interface UserService {

	User loginUser(String username, String password);
}
