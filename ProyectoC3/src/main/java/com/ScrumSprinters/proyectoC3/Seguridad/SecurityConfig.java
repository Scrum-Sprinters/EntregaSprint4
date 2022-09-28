package com.ScrumSprinters.proyectoC3.Seguridad;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin@scrumsprinter.com")
                .password("{noop}admin1234")
                .roles("ADMIN", "USER")
                .and()
                .withUser("user@scrumsprinter.com")
                .password("{noop}user5678")
                .roles("USER")
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()

//                .antMatchers("/usuario/delete/**").hasAnyRole("ADMIN")
                .antMatchers("/users/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/enterprises/**").hasAnyRole("ADMIN")
//                .antMatchers("/enterprises/**").hasAnyRole("ADMIN","USER")

                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()


                .and()


                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")


                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")

                .and()

                .exceptionHandling()
                .accessDeniedPage("/errores/403")
        ;



    }
}
