package com.jiecode.srpingboot.demosecurity.sercurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
public class DemoSecurityConfig {
// add support for JDBC no motr hard code users

    /*
    实现了自定义数据库的用户认证和授权，通过jdbcUserdetailsManager和知道的查询语句
    根据用户名查询用户信息
    根据用户名查询用户角色（决定用户可以访问哪些资源
     */
    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        //define query to retrivev a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id,pw,active form mebers where user_id=? ");
        //define query to retrieve a authoritites/roles by Username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id,role from roles where user_id=? "
        );

        return jdbcUserDetailsManager;
    }






    /*
    the annotations tells spring to treat the method as a bean definition
    the method will return an object that spring will manage ans use within the application
    context
     */

    @Bean
    /*
     is used to cocnfigure HTTP
     sercurity for the spring boot application
     the HttpSecurity object is passed into the method, allowing you to define security rules for request
     like authentication验证, authorization 授权
     */
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
         http.autrizeHttpRequests this configures the authorization ruels for Http requests
         configurer.anyrequest any request to the qpplication must be authenticated
         */
        http.authorizeHttpRequests(configurer->
                        configurer.requestMatchers("/").hasRole("EMPLOYEE")
                                .requestMatchers("/leaders/**").hasRole("MANAGER")
                                .requestMatchers("/systems/**").hasRole("ADMIN")

                                .anyRequest().authenticated()
                )

                /*
                 formLogin form based login for the application
                 form.loginPage("/showLoginPage")this specifies the URL(/showMyLoginpage)
                 of the custom login page that the user will be redirected to if they try to
                 access a protected resource while not loggedin
                 form.loginProcessingUrl("/authenticateTheUser"): This is the URL where Spring Security will submit the login form.
                  When the user submits their login credentials, Spring Security will process the authentication at this URL (/authenticateTheUser).
                 permitAll(): This allows everyone (even unauthenticated users)
         to access the login page and the login processing URL without restriction.
              So, the login page and authentication endpoint are open to all.
                 */
                .formLogin(form->
                        form.loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()

                )
                .logout(logout->
                        logout.permitAll()
                )
                .exceptionHandling(configurer->
                configurer.accessDeniedPage("/access-denied")
                );
        /*
        his line builds and returns the SecurityFilterChain configuration.
        Once the configuration is set up (authentication and authorization rules, login page setup),
        calling http.build() finalizes the configuration.
         */
        return http.build();
    }

/*
    //add users,passward ,role

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER","ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john,mary,susan);


    }

     */

}
