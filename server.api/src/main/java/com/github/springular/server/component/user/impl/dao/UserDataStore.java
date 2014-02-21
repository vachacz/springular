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
		
		createUser("stoch", "Kamil","Stoch", Nationality.POLAND);
		createUser("zyla", "Piotr","Zyla",Nationality.POLAND);
		createUser("ziober", "Jan","Ziobro",Nationality.POLAND);
		createUser("hula", "Stefan","Hula",Nationality.POLAND);
		createUser("klimek", "Klemens","Muranka",Nationality.POLAND);
		createUser("kuba", "Dawid","Kubacki",Nationality.POLAND);
		createUser("biegun", "Krzysztof","Biegun",Nationality.POLAND);
		createUser("kot", "Maciej","Kot",Nationality.POLAND);
		createUser("destroy", "Aleksander","Zniszczol",Nationality.POLAND);
		createUser("bardal", "Anders","Bardal",Nationality.NORWAY);
		createUser("hilde", "Tom","Hilde",Nationality.NORWAY);
		createUser("velta", "Rune","Velta",Nationality.NORWAY);
		createUser("stjernen", "Anders","Stjernen",Nationality.NORWAY);
		createUser("friday", "Richard","Freitag",Nationality.GERMANY);
		createUser("hannawald", "Sven","Hannawald",Nationality.GERMANY);
		createUser("freund", "Severin","Freund",Nationality.GERMANY);
		createUser("jacobsen", "Anders","Jacobsen",Nationality.NORWAY);
		createUser("fanny", "Anders","Fannemel",Nationality.NORWAY);
		createUser("neumayer", "Michael","Neumeyer",Nationality.GERMANY);
		createUser("schmitt", "Martin","Schmitt",Nationality.GERMANY);
		createUser("wank", "Andreas","Wank",Nationality.GERMANY);
		createUser("wellinger", "Andreas","Wellinger",Nationality.GERMANY);
	}
	
	public List<UserDO> getUsers() {
		return new ArrayList<UserDO>(userDB.values());
	}

	public UserDO findUser(String id) {
		return userDB.get(id);
	}

  public void deleteUser(String userId) {
    userDB.remove(userId);
  }
	
	private void createUser(String login, String firstName, String lastName, Nationality nationality) {
		UserDO user = new UserDO();
		user.setId(Math.abs(random.nextInt()) + "");
		user.setLogin(login);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setNationality(nationality.code());
		
		userDB.put(user.getId(), user);
	}

}
