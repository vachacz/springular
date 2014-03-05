package com.github.springular.server.component.auth.impl;

import com.github.springular.server.component.auth.IAuthenticationBCI;
import com.github.springular.server.component.auth.UserCredentialsDO;

public class AuthenticationBCI implements IAuthenticationBCI {

  @Override
  public UserCredentialsDO findCredentialsByUsername(String username) {
    switch (username) {
      case "admin": return credentials("admin", "admin");
      case "user1": return credentials("user1", "user1");
      case "user2": return credentials("user2", "user2");
    }
    return null;
  }

  private UserCredentialsDO credentials(String login, String password) {
    return new UserCredentialsDO(login, password);
  }

}
