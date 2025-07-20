package com.murathnakts.controller.impl;

import com.murathnakts.controller.IUserController;
import com.murathnakts.dto.DtoRefreshToken;
import com.murathnakts.dto.DtoToken;
import com.murathnakts.dto.DtoUser;
import com.murathnakts.services.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements IUserController {

    @Autowired
    private IUserService userService;

    @PostMapping(path = "/register")
    @Override
    public DtoUser register(@Valid @RequestBody DtoUser request) {
        return userService.register(request);
    }

    @PostMapping(path = "/authenticate")
    @Override
    public DtoToken authenticate(@Valid @RequestBody DtoUser request) {
        return userService.authenticate(request);
    }

    @PostMapping(path = "/refresh-token")
    @Override
    public DtoToken refreshToken(@RequestBody DtoRefreshToken refreshToken) {
        return userService.refreshToken(refreshToken);
    }
}
