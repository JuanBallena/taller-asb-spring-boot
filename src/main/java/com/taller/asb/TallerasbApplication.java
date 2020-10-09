package com.taller.asb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.taller.asb.repository.CustomRepositoryImpl;

//import org.springframework.http.HttpMethod;
//import com.taller.asb.util.JWTAuthorizationFilter;

@SpringBootApplication
@EnableJpaRepositories (repositoryBaseClass = CustomRepositoryImpl.class)//For refresh in repositories
@EnableScheduling
@EnableAsync
public class TallerasbApplication {

	public static void main(String[] args) {
		SpringApplication.run(TallerasbApplication.class, args);

	}
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.cors().and().csrf().disable();
				//.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				//.authorizeRequests()
				//.antMatchers(HttpMethod.POST, "/login").permitAll()
				//.anyRequest().authenticated();
		}
	}

}
