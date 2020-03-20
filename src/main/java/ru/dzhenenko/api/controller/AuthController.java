package ru.dzhenenko.api.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dzhenenko.api.converter.ServiceUserToResponseConverter;
import ru.dzhenenko.api.json.AuthRequest;
import ru.dzhenenko.api.json.AuthResponse;
import ru.dzhenenko.entity.User;
import ru.dzhenenko.repository.ServiceUserRepository;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {
    private final ServiceUserRepository serviceUserRepository;
    private final ServiceUserToResponseConverter converter;
    private final AuthService authService;

    @GetMapping("/get-user-info")
    public @ResponseBody
    ResponseEntity<AuthResponse> getUserInfo(HttpServletRequest request, User user) {
        /*HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");*/
        UserDTO userId = authService.currentUser();
        if (userId == null) {
            return status(HttpStatus.UNAUTHORIZED).build();
        }

        User user1 = serviceUserRepository.getOne(userId.getId());
        if (user1 == null) {
            return status(HttpStatus.UNAUTHORIZED).build();
        }
        return ok(converter.convert(user1));
    }
}