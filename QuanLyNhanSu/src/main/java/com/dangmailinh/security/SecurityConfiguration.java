package com.dangmailinh.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dangmailinh.service.LoginService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	LoginService loginService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
		authenticationManagerBuilder.userDetailsService(this.loginService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.authorizeRequests().antMatchers("/upload/**","/trangdangnhap/**","/register/**","/bower_components/**","/css/**","/dist/**","/js/**","/plugins/**","/time/**").permitAll()
		.antMatchers("/quantri/**").hasAnyRole("USER","ADMIN")
		.antMatchers("/admin/**").hasRole("ADMIN")
		.and().formLogin()
		.loginPage("/trangdangnhap")
		.loginProcessingUrl("/login")
		.usernameParameter("email")
		.passwordParameter("password")
		.defaultSuccessUrl("/quantri")
		.failureUrl("/loginfailure")
		.and().logout()
		.logoutUrl("/logout")
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID").permitAll()
		.and().exceptionHandling().accessDeniedPage("/accessdenied");
	}
}
