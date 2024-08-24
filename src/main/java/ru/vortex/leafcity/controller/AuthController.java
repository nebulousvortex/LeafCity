package ru.vortex.leafcity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vortex.leafcity.jwt_manager.JwtRequest;
import ru.vortex.leafcity.jwt_manager.JwtResponse;
import ru.vortex.leafcity.jwt_manager.RefreshJwtRequest;
import ru.vortex.leafcity.model.user.Role;
import ru.vortex.leafcity.model.user.User;
import ru.vortex.leafcity.service.AuthService;
import ru.vortex.leafcity.service.RoleService;
import ru.vortex.leafcity.service.UserService;
import ru.vortex.leafcity.utils.Validator;

import javax.security.auth.message.AuthException;
import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    Validator validator;

    @PostMapping("role")
    public ResponseEntity<?> login(@RequestBody Role role) {
        roleService.createRole(role);
        return ResponseEntity.ok("Okay");
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody JwtRequest authRequest) {
        return authService.login(authRequest);
    }

    @PostMapping("token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@Valid @RequestBody RefreshJwtRequest request) {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("refresh")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@Valid @RequestBody RefreshJwtRequest request) throws AuthException {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("registration")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return validator.getErrorList(bindingResult);
        } else {
            if (!user.getPassword().equals(user.getPasswordConfirm())) {
                return ResponseEntity.ok("Пароли не совпадают");
            }
            if (!userService.saveUser(user)) {
                return ResponseEntity.ok("Пользователь с таким именем уже существует");
            }
            return ResponseEntity.ok("Зарегестрирован");
        }
    }

}
