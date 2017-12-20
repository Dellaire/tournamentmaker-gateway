package de.jet.tournamentmaker.gateway.model;

import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "user")
public class TournamentUser
{
	private String password;
	private final String username;
	private final Set<GrantedAuthority> authorities;
	private final boolean accountNonExpired;
	private final boolean accountNonLocked;
	private final boolean credentialsNonExpired;
	private final boolean enabled;

	@JsonCreator
	public TournamentUser(@JsonProperty("password") String password, @JsonProperty("username") String username,
			@JsonProperty("authorities") Set<GrantedAuthority> authorities,
			@JsonProperty("accountNonExpired") boolean accountNonExpired,
			@JsonProperty("accountNonLocked") boolean accountNonLocked,
			@JsonProperty("credentialsNonExpired") boolean credentialsNonExpired,
			@JsonProperty("enabled") boolean enabled)
	{
		this.password = password;
		this.username = username;
		this.authorities = authorities;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
	}

	public String getPassword()
	{
		return password;
	}

	public String getUsername()
	{
		return username;
	}

	public Set<GrantedAuthority> getAuthorities()
	{
		return authorities;
	}

	public boolean isAccountNonExpired()
	{
		return accountNonExpired;
	}

	public boolean isAccountNonLocked()
	{
		return accountNonLocked;
	}

	public boolean isCredentialsNonExpired()
	{
		return credentialsNonExpired;
	}

	public boolean isEnabled()
	{
		return enabled;
	}
}
