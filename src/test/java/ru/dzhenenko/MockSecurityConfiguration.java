package ru.dzhenenko;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.dzhenenko.secuity.CustomGrantedAuthority;
import ru.dzhenenko.secuity.CustomUserDetails;
import ru.dzhenenko.secuity.UserRole;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MockSecurityConfiguration {
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
                List<CustomGrantedAuthority> roles =  new ArrayList<>();
                roles.add(new CustomGrantedAuthority(UserRole.USER));
                return new CustomUserDetails(
                        1L,
                        "i.dzhenenko@gmail.com",
                        "qwerty",
                        roles
                );
            }
        };
    }
}
