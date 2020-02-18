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

    @PostMapping("/login")
    public @ResponseBody
    ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request, User user,
                                       HttpServletRequest httpServletRequest) {

        User user1 = serviceUserRepository.findByEmailAndPassword(
                request.getEmail(),
                DigestUtils.md5Hex(request.getPassword())
        );
        if (user1 == null) {
            return status(HttpStatus.UNAUTHORIZED).build();
        }
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("userId", user1.getId());
        return ok(converter.convert(user1));

    }

    @GetMapping("/get-user-info")
    public @ResponseBody
    ResponseEntity<AuthResponse> getUserInfo(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return status(HttpStatus.UNAUTHORIZED).build();
        }

        User user1 = serviceUserRepository.getOne(userId);
        if (user1 == null) {
            return status(HttpStatus.UNAUTHORIZED).build();
        }
        return ok(converter.convert(user1));
    }
}