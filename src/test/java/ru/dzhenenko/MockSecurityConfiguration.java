package ru.dzhenenko;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.dzhenenko.secuity.CustomGrantedAuthority;
import ru.dzhenenko.secuity.CustomUserDetails;
import ru.dzhenenko.secuity.UserRole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//@Profile("Production")
@Configuration
public class MockSecurityConfiguration {
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

                CustomGrantedAuthority grantedAuthority = new CustomGrantedAuthority(UserRole.USER);
                return new CustomUserDetails(
                        1L,
                        "i.dzhenenko@gmail.com",
                        "qwerty",
                        Collections.singleton(grantedAuthority)
                );
            }
        };
    }
}
