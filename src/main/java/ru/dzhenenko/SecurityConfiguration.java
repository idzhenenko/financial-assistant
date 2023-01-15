package ru.dzhenenko;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static ru.dzhenenko.secuity.UserRole.ADMIN;
import static ru.dzhenenko.secuity.UserRole.USER;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/login-form", "/registration", "/add-user").permitAll()
                .antMatchers("/personal-area").hasAnyRole(USER.name(), ADMIN.name())
                .antMatchers("/add-account", "/delete-account", "/view-account").hasAnyRole(USER.name(), ADMIN.name())
                .antMatchers("/add-type-account", "/delete-type-account", "/view-type-account", "rename-type-account").hasAnyRole(USER.name(), ADMIN.name())
                .antMatchers("/insert-new-transaction", "/report-account-category").hasAnyRole(USER.name(), ADMIN.name())
                .antMatchers("/api/get-user-info", "/api/view-account", "/api/delete-account",
                        "/api/add-account", "/api/add-type-account", "/api/delete-type-account", "/api/registration",
                        "/api/edit-type-account", "/api/view-report").hasAnyRole(USER.name(), ADMIN.name())
                .and()
                .formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/login-form")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/personal-area")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login-form")
                .and()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}