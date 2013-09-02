package com.incra.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * The <i>MyUserDetails</i> DTO augments the standard Spring security
 * UserDetails object to have additional information used to drive the security
 * policy.
 * 
 * At the moment, this information includes the user id, email.
 * 
 * @author Jeff Risberg
 * @since 09/10/11
 */
public class MyUserDetails extends
    org.springframework.security.core.userdetails.User {
  private static final long serialVersionUID = 0L;

  private Long id;
  private String email;

  public MyUserDetails(String username, String password, boolean enabled,
      boolean accountNonExpired, boolean credentialsNonExpired,
      boolean accountNonLocked,
      Collection<? extends GrantedAuthority> authorities, Long id, String email) {
    super(username, password, enabled, accountNonExpired,
        credentialsNonExpired, accountNonLocked, authorities);

    this.id = id;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }
}
