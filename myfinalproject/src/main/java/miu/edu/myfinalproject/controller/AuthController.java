package miu.edu.myfinalproject.controller;

import miu.edu.myfinalproject.dto.LogInResponseDTO;
import miu.edu.myfinalproject.dto.LoginRequestDTO;
import miu.edu.myfinalproject.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authenticate")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) throws Exception {
        var loginResponse = authService.login(loginRequest);
        return new ResponseEntity<LogInResponseDTO>(
                loginResponse, HttpStatus.OK);
    }
}
