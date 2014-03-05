package com.github.springular.server.component.auth.impl;

import com.github.springular.server.component.auth.IAuthenticationBCI;
import com.github.springular.server.component.auth.UserCredentialsDO;

public class AuthenticationBCI implements IAuthenticationBCI {

  @Override
  public UserCredentialsDO findCredentialsByUsername(String username) {
    if ("admin".equals(username)) return credentials("admin", "admin");
    return null;
  }

  private UserCredentialsDO credentials(String login, String password) {
    return new UserCredentialsDO(login, password);
  }

}
