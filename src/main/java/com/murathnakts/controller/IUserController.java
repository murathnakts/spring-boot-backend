package com.murathnakts.controller;

import com.murathnakts.dto.DtoRefreshToken;
import com.murathnakts.dto.DtoToken;
import com.murathnakts.dto.DtoUser;

public interface IUserController {

    public DtoUser register(DtoUser request);

    public DtoToken authenticate(DtoUser request);

    public DtoToken refreshToken(DtoRefreshToken refreshToken);
}
