package ru.dzhenenko.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dzhenenko.api.converter.Converter;
import ru.dzhenenko.entity.User;
import ru.dzhenenko.repository.ServiceUserRepository;
import ru.dzhenenko.secuity.CustomUserDetails;
import ru.dzhenenko.secuity.UserRole;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final Converter<User, UserDTO> userDtoConverter;
    private final ServiceUserRepository serviceUserRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDTO getUserById(Long userId) {
        User user = serviceUserRepository.getOne(userId);
        if (user == null) {
            return null;
        }
        return userDtoConverter.convert(user);
    }

    public UserDTO auth(String email, String password) {
        String hash = passwordEncoder.encode(password);
        User user = serviceUserRepository.findByEmailAndPassword(email, hash);

        if (user == null) {
            return null;
        }
        return userDtoConverter.convert(user);
    }


    public UserDTO registration(String firstName, String lastName, String phone, String email,
                                String password) {
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(UserRole.USER);
        String hash = passwordEncoder.encode(password);

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(hash);
        user.setRoles(userRoles);

        User userSave = serviceUserRepository.save(user);

        if (userSave == null) {
            return null;
        }

        return userDtoConverter.convert(userSave);
    }

    public UserDTO currentUser() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = serviceUserRepository.getOne(customUserDetails.getId());
        return userDtoConverter.convert(user);
    }

}