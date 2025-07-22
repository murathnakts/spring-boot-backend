package com.murathnakts.services;

import com.murathnakts.dto.DtoRefreshToken;
import com.murathnakts.dto.DtoToken;
import com.murathnakts.dto.DtoUser;

public interface IUserService {

    public DtoUser register(DtoUser request);

    public DtoToken authenticate(DtoUser request);

    public DtoToken refreshToken(DtoRefreshToken refreshToken);
}
