package pe.edu.upc.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pe.edu.upc.spring.auth.handler.LoginSuccessHandler;
import pe.edu.upc.spring.serviceimpl.JpaUserDetailsService;;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JpaUserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private LoginSuccessHandler successHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		try {
			http.authorizeRequests()
				.antMatchers("/", "/index").permitAll()
				.antMatchers("/sizes/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/colors/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/marks/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/commerces/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/clothingTypes/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/clothings/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/discounts/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/customers/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/assessors/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/preferences/**").access("hasRole('ROLE_CSTMR')")
				.antMatchers("/consultings/**").access("hasRole('ROLE_CSTMR')")
				.antMatchers("/sales/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_CSTMR')").and()
				.formLogin().successHandler(successHandler).loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/")
				.permitAll().and().logout().logoutSuccessUrl("/login").permitAll().and().exceptionHandling().accessDeniedPage("/error_403");
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
}
