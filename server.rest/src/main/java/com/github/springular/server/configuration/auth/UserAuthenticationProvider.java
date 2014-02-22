package com.github.springular.server.configuration.auth;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.github.springular.server.component.auth.IAuthenticationBCI;
import com.github.springular.server.component.auth.UserCredentialsDO;

public class UserAuthenticationProvider implements UserDetailsService {

  @Autowired
  private IAuthenticationBCI authBCI;
  
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserCredentialsDO userCredentials = authBCI.findCredentialsByUsername(username);
    if (userCredentials == null) {
        throw new UsernameNotFoundException("Invalid username/password.");
    }
    Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("USER");
    return new User(userCredentials.getUsername(), userCredentials.getPassword(), authorities);
  }
  
}