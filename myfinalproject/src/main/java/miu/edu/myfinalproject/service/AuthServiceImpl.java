package miu.edu.myfinalproject.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import miu.edu.myfinalproject.dto.LogInResponseDTO;
import miu.edu.myfinalproject.dto.LoginRequestDTO;
import miu.edu.myfinalproject.entity.Employee;
import miu.edu.myfinalproject.repository.EmployeeRepository;
import miu.edu.myfinalproject.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class AuthServiceImpl implements AuthService {


    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
                           JwtUtil jwtUtil, EmployeeRepository employeeRepository){//, BCryptPasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.employeeRepository = employeeRepository;
//        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LogInResponseDTO login(LoginRequestDTO loginRequest) throws Exception {

        // data type from security dependency
        Authentication result = null;

        try {
            // Authentication manager is an interface that comes with spring security authentication package
//            String encodedPass = passwordEncoder.encode(loginRequest.getPassword());
//            System.out.println("encodedPass = "+encodedPass);
            result = authenticationManager.authenticate(
                    // also comes with spring security
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(result.getName());
//        userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        final String accessToken = jwtUtil.generateToken(userDetails);
        Employee users = employeeRepository.getEmployeeByEmail(loginRequest.getEmail());
        return new LogInResponseDTO( users.getId(), users.getEmail(), accessToken);
    }

}
