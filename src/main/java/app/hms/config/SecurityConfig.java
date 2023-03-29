package app.hms.config;

import app.hms.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity//enable Spring Security in a Spring Boot application and to configure the security settings for web applications.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/patients/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/patients/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/auth/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    /*@Override
    @Bean
    protected UserDetailsService userDetailsService() {//The UserDetailsService interface is part of Spring Security and defines the contract for a service that can load user details by username.
        UserDetails kiran = User.builder().username("kiran").password(passwordEncoder().encode("kiran")).roles("ADMIN").build();//Both users are stored as instances of the UserDetails interface, which is returned by the userDetailsService() method when it is called
        UserDetails kavya = User.builder().username("kavya").password(passwordEncoder().encode("kavya")).roles("USER").build();
        return new InMemoryUserDetailsManager(kiran,kavya);//The InMemoryUserDetailsManager is a class provided by Spring Security that implements the UserDetailsService interface and allows developers to define user details directly in memory.
    }*/
}
