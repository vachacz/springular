package com.github.springular.server.component.user.impl.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;

import com.github.springular.server.component.user.Nationality;
import com.github.springular.server.component.user.UserDO;

public class UserDataStore {

	private Random random;
	private Map<String, UserDO> userDB;
	
	@PostConstruct
	public void postConstruct() {
		random = new Random();
		userDB = new HashMap<String, UserDO>();
		
		createUserWithLogin("stoch", Nationality.POLAND);
		createUserWithLogin("zyla", Nationality.POLAND);
		createUserWithLogin("ziobro", Nationality.POLAND);
		createUserWithLogin("hula", Nationality.POLAND);
		createUserWithLogin("muranka", Nationality.POLAND);
		createUserWithLogin("kubacki", Nationality.POLAND);
		createUserWithLogin("biegun", Nationality.POLAND);
		createUserWithLogin("kot", Nationality.POLAND);
		createUserWithLogin("zniszczol", Nationality.POLAND);
		createUserWithLogin("bardal", Nationality.NORWAY);
		createUserWithLogin("hilde", Nationality.NORWAY);
		createUserWithLogin("velta", Nationality.NORWAY);
		createUserWithLogin("stjernen", Nationality.NORWAY);
		createUserWithLogin("freitag", Nationality.GERMANY);
		createUserWithLogin("hannawald", Nationality.GERMANY);
		createUserWithLogin("freund", Nationality.GERMANY);
		createUserWithLogin("jacobsen", Nationality.NORWAY);
		createUserWithLogin("fannemel", Nationality.NORWAY);
		createUserWithLogin("neumayer", Nationality.GERMANY);
		createUserWithLogin("schmitt", Nationality.GERMANY);
		createUserWithLogin("wank", Nationality.GERMANY);
		createUserWithLogin("wellinger", Nationality.GERMANY);
	}
	
	public List<UserDO> getUsers() {
		return new ArrayList<UserDO>(userDB.values());
	}

	public UserDO findUser(String id) {
		return userDB.get(id);
	}
	
	private void createUserWithLogin(String login, Nationality nationality) {
		UserDO user = new UserDO();
		user.setId(random.nextInt() + "");
		user.setLogin(login);
		user.setFirstName("firstname_" + login);
		user.setLastName("lastname_" + login);
		user.setNationality(nationality.code());
		
		userDB.put(user.getId(), user);
	}

}
