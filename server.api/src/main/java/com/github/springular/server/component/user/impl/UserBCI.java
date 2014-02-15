package com.github.springular.server.component.user.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.springular.server.component.user.IUserBCI;
import com.github.springular.server.component.user.UserDO;
import com.github.springular.server.component.user.impl.dao.UserDataStore;
import com.github.springular.server.exception.BusinessException;
import com.github.springular.server.exception.BusinessException.Builder;

public class UserBCI implements IUserBCI {

	@Autowired
	UserDataStore userDataStore;

	@Override
	public List<UserDO> getUsers() {
		return userDataStore.getUsers();
	}

	@Override
	public void updateUser(@Valid UserDO user) {
		UserDO persistentUser = userDataStore.findUser(user.getId());

		Builder errorBuilder = BusinessException.build();

		if (isUpper(user.getFirstName())) {
			errorBuilder.addMessage("Uppercase first name is not allowed");
		}

		if (isUpper(user.getLastName())) {
			errorBuilder.addMessage("Uppercase last name is not allowed");
		}

		if (errorBuilder.hasMessages()) {
			throw errorBuilder.excetpion();
		}

		persistentUser.setLogin(user.getLogin());
		persistentUser.setFirstName(user.getFirstName());
		persistentUser.setLastName(user.getLastName());
	}

	public static boolean isUpper(String s) {
		for (char c : s.toCharArray()) {
			if (!Character.isUpperCase(c))
				return false;
		}
		return true;
	}

}
