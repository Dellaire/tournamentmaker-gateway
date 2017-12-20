package de.jet.tournamentmaker.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private CsrfTokenResponseHeaderBindingFilter csrfTokenResponseHeaderBindingFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests().antMatchers("/").authenticated().and().formLogin();

		http.csrf().ignoringAntMatchers("/matchmaking/**");
		http.addFilterAfter(csrfTokenResponseHeaderBindingFilter, CsrfFilter.class);
	}
}