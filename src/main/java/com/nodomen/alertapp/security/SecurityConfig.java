package com.nodomen.alertapp.security;

import com.nodomen.alertapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").authenticated() //Для любого запроса нужно быть аутентифицированым
                .antMatchers("/admin/**").hasRole("ADMIN") //В админку может зайти пользователь с ролью Администратор
                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/login");


    }

//    In-Memory example
//    @Bean
//    protected UserDetailsService users() {
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$12$b9eSaDhPyc.R3SQxkAXXW.8ZUft107VFYsmXGZ9cAIQg3QTtBVscG")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$12$b9eSaDhPyc.R3SQxkAXXW.8ZUft107VFYsmXGZ9cAIQg3QTtBVscG")
//                .roles("ADMIN", "USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//
//    jdbc example
//    @Bean
//    public JdbcUserDetailsManager users(DataSource dataSource) {
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$12$b9eSaDhPyc.R3SQxkAXXW.8ZUft107VFYsmXGZ9cAIQg3QTtBVscG")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$12$b9eSaDhPyc.R3SQxkAXXW.8ZUft107VFYsmXGZ9cAIQg3QTtBVscG")
//                .roles("ADMIN", "USER")
//                .build();
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        users.createUser(user);
//        users.createUser(admin);
//        return users;
//    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }
}

//TODO написать схему устройства Spring Security