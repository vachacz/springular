package pl.vachacz.angular.server;

import java.util.List;

public interface IUserBCI {

	public List<UserDO> getUsers();
	public UserDO findUser(String login);
	
	public void updateUser(UserDO user);
	
	public void login(CredentialsDO credentials);
	
}
