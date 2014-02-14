package pl.vachacz.angular.server.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import pl.vachacz.angular.server.CredentialsDO;
import pl.vachacz.angular.server.IUserBCI;
import pl.vachacz.angular.server.Nationality;
import pl.vachacz.angular.server.UserDO;
import pl.vachacz.angular.server.exception.BusinessException;
import pl.vachacz.angular.server.exception.BusinessException.Builder;

public class UserBCI implements IUserBCI {
	
	private Map<String, UserDO> userDB;
	private Random random;

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
	
	@Override
	public List<UserDO> getUsers() {
		return new ArrayList<UserDO>(userDB.values());
	}
	
	@Override
	public UserDO findUser(String id) {
		return userDB.get(id);
	}

	@Override
	public void login(CredentialsDO credentials) {
	}

	@Override
	public void updateUser(@Valid UserDO user) {
		UserDO persistentUser = findUser(user.getId());
		
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
	
	private void createUserWithLogin(String login, Nationality nationality) {
		
		UserDO user = new UserDO();
		user.setId(random.nextInt() + "");
		user.setLogin(login);
		user.setFirstName("firstname_" + login);
		user.setLastName("lastname_" + login);
		user.setNationality(nationality.code());
		
		userDB.put(user.getId(), user);
	}
	
	public static boolean isUpper(String s)
	{
	    for(char c : s.toCharArray())
	    {
	        if(! Character.isUpperCase(c))
	            return false;
	    }

	    return true;
	}

}
