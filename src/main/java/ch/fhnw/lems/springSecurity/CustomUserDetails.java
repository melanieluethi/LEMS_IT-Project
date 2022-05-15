package ch.fhnw.lems.springSecurity;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ch.fhnw.lems.entity.User;

// LUM
// https://www.codejava.net/frameworks/spring-boot/user-registration-and-login-tutorial
public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = -4927368631753405266L;
	private String username;
	private String password;
	private String role;

	public CustomUserDetails(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.role = user.getRole().getRole().name();
	}

	// https://stackoverflow.com/questions/32276482/java-lang-classcastexception-org-springframework-security-core-userdetails-user
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_" + this.role);
		ArrayList<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(role);
		return roles;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
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