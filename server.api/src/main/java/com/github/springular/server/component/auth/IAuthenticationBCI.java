package com.github.springular.server.component.auth;

public interface IAuthenticationBCI {

  UserCredentialsDO findCredentialsByUsername(String username);

}
