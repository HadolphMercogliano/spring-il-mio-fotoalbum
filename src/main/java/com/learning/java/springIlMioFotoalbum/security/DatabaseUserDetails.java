package com.learning.java.springIlMioFotoalbum.security;

import com.learning.java.springIlMioFotoalbum.model.Role;
import com.learning.java.springIlMioFotoalbum.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DatabaseUserDetails implements UserDetails {
  
  private final String username;
  private final String password;
  private final Set<GrantedAuthority> authorities;
  public DatabaseUserDetails(User user) {
    this.username = user.getEmail();
    this.password = user.getPassword();
    
    this.authorities = new HashSet<>();
    for (Role role : user.getRoles()) {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    }
  }
  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }
  
  @Override
  public String getPassword() {
    return password;
  }
  
  @Override
  public String getUsername() {
    return username;
  }
  
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }
  
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }
  
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
  
  @Override
  public boolean isEnabled() {
    return true;
  }
}
