package miu.edu.myfinalproject.service;

import miu.edu.myfinalproject.dto.*;

public interface AuthService {

    LogInResponseDTO login(LoginRequestDTO loginRequest) throws Exception;
//    LogInResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequest);
}
