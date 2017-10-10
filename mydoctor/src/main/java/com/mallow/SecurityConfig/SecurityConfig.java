package com.mallow.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by brahim on 8/12/17.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void globalConfig(AuthenticationManagerBuilder auth, @Qualifier("dataSource") DataSource dataSource) throws Exception{
       /* auth.inMemoryAuthentication().withUser("brahim").password("brahim").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("doctor").password("doctor").roles("PATIENT", "DOCTOR");
        auth.inMemoryAuthentication().withUser("secretary").password("secretary").roles("SECRETARY", "PATIENT");
        */

       auth.jdbcAuthentication()
               .dataSource(dataSource)
               .usersByUsernameQuery("select username as principal, password as credentials, true from person where username = ?")
               .authoritiesByUsernameQuery("select person_username as principal, roles_name as role from persons_roles where person_username = ? ")
               .rolePrefix("ROLE_");
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/register.html", "/save", "/person", "/addPatient", "/registerp", "/loginclient", "/infoPatient", "/mydoctors", "/mydoctordetails");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http

                .csrf().disable()


                .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/images/**").permitAll()
                .anyRequest()
                .authenticated()

                .and()

                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/index")

                .failureUrl("/error.html");






    }


}
