package de.jet.tournamentmaker.gateway;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Value("${csrf.ignore.paths}")
	private String csrfIgnorePaths = "";

	@Autowired
	private CsrfTokenResponseHeaderBindingFilter csrfTokenResponseHeaderBindingFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests().antMatchers("/").authenticated().and().formLogin();

		if (!csrfIgnorePaths.isEmpty())
		{
			List<String> pathsToIgnore = Arrays.asList(csrfIgnorePaths.split(","));
			pathsToIgnore.forEach(path ->
			{
				try
				{
					http.csrf().ignoringAntMatchers(path);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			});
		}

		http.addFilterAfter(csrfTokenResponseHeaderBindingFilter, CsrfFilter.class);
	}
}