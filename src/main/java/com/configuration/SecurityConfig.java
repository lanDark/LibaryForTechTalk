/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.configuration;

import com.Filter.EncodingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

/**
 *
 * @author vital
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"com.ServiceImpl","com.securityImpl"})    
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserDetailsService userDetailsServiceImpl;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }
 
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
            http.addFilterBefore(new EncodingFilter(), ChannelProcessingFilter.class);
	    http.authorizeRequests()
                .antMatchers("/libarian/**","/Libarian/**").hasRole("LIBARIAN")
                .antMatchers("/datMuon**").hasRole("USER")
                    .antMatchers("/CartView**").hasRole("USER")
                .and()
                .formLogin()
		.loginPage("/Login")
		.usernameParameter("email")
		.passwordParameter("password")
                .defaultSuccessUrl("/Login")
                .failureUrl("/Login?error=true")
		.and().logout().logoutUrl("/Logout_User").invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/")
                .and().rememberMe().rememberMeParameter("rememberme").key("NguyenTheLan29051997").tokenValiditySeconds(1209600)

		.and().exceptionHandling().accessDeniedPage("/403")
                .and().csrf().disable();
    }
     
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    //https://hellokoding.com/registration-and-login-example-with-spring-security-spring-boot-spring-data-jpa-hsql-jsp/
    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }
}
