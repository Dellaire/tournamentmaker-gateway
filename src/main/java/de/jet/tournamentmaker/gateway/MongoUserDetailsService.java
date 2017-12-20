package de.jet.tournamentmaker.gateway;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.jet.tournamentmaker.gateway.model.TournamentUser;
import de.jet.tournamentmaker.gateway.persistence.UserRepository;

@Component
public class MongoUserDetailsService implements UserDetailsService
{
	private final UserRepository userRepository;
	private final ObjectMapper objectMapper;

	public MongoUserDetailsService(UserRepository userRepository, ObjectMapper objectMapper) throws Exception
	{
		this.userRepository = Objects.requireNonNull(userRepository);
		this.objectMapper = Objects.requireNonNull(objectMapper);

		String defaultUserString = new String(Files.readAllBytes(Paths.get("defaultUsers")));
		TournamentUser defaultUser = this.objectMapper.readValue(defaultUserString, TournamentUser.class);
		this.userRepository.save(defaultUser);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		TournamentUser tournamentUser = this.userRepository.findByUsername(username);

		return new User(tournamentUser.getUsername(), tournamentUser.getPassword(), tournamentUser.isEnabled(),
				tournamentUser.isAccountNonExpired(), tournamentUser.isCredentialsNonExpired(),
				tournamentUser.isAccountNonLocked(), tournamentUser.getAuthorities());
	}
}
