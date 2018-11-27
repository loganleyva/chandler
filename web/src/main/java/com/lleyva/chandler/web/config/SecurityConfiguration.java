package com.lleyva.chandler.web.config;

import com.lleyva.chandler.data.enums.AccountRole;
import com.lleyva.chandler.services.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AccountsService accountsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	///////////////
	// Overrides //
	///////////////

	@Override
	protected void configure(
			AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
				.userDetailsService(accountsService)
				.passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(
			HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.authorizeRequests()
					.antMatchers("/api/v1/posts/**")
						.hasAnyRole(AccountRole.ADMIN.toString(), AccountRole.USER.toString())
					.antMatchers("/api/v1/accounts/**")
						.hasRole(AccountRole.ADMIN.toString())
					.anyRequest().denyAll()
				.and().httpBasic();
	}

}
