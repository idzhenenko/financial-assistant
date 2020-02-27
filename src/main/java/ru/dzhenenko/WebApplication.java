package ru.dzhenenko;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.dzhenenko.view.TerminalView;

import java.sql.SQLException;

import static ru.dzhenenko.secuity.UserRole.ADMIN;
import static ru.dzhenenko.secuity.UserRole.USER;

@SpringBootApplication
public class WebApplication extends WebSecurityConfigurerAdapter implements CommandLineRunner {

    @Autowired
    private TerminalView terminalView;

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(WebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        terminalView.start();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                        .antMatchers("/login-form").permitAll()
                        .antMatchers("/personal-area").hasAnyRole(USER.name(), ADMIN.name())
                .and()
                        .formLogin()
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .loginPage("login-form")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/personal-area")
                .and()
                    .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("login-form");
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}