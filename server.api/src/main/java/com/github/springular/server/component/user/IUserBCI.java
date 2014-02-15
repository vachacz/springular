package com.github.springular.server.component.user;

import java.util.List;

public interface IUserBCI {

	public List<UserDO> getUsers();
	
	public void updateUser(UserDO user);
	
}
