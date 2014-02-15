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
		
		createUserWithLogin("stoch", Nationality.POLAD);
		createUserWithLogin("zyla", Nationality.POLAD);
		createUserWithLogin("ziobro", Nationality.POLAD);
		createUserWithLogin("hula", Nationality.POLAD);
		createUserWithLogin("muranka", Nationality.POLAD);
		createUserWithLogin("kubacki", Nationality.POLAD);
		createUserWithLogin("biegun", Nationality.POLAD);
		createUserWithLogin("kot", Nationality.POLAD);
		createUserWithLogin("zniszczol", Nationality.POLAD);
		createUserWithLogin("bardal", Nationality.NORWAY);
		createUserWithLogin("hilde", Nationality.NORWAY);
		createUserWithLogin("velta", Nationality.NORWAY);
		createUserWithLogin("stjernen", Nationality.NORWAY);
		createUserWithLogin("freitag", Nationality.GERMAN);
		createUserWithLogin("hannawald", Nationality.GERMAN);
		createUserWithLogin("freund", Nationality.GERMAN);
		createUserWithLogin("jacobsen", Nationality.NORWAY);
		createUserWithLogin("fannemel", Nationality.NORWAY);
		createUserWithLogin("neumayer", Nationality.GERMAN);
		createUserWithLogin("schmitt", Nationality.GERMAN);
		createUserWithLogin("wank", Nationality.GERMAN);
		createUserWithLogin("wellinger", Nationality.GERMAN);
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
