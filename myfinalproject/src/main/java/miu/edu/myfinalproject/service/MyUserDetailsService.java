package miu.edu.myfinalproject.service;


import jakarta.transaction.Transactional;
import miu.edu.myfinalproject.repository.EmployeeRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("userDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    private final EmployeeRepository userRepo;

    public MyUserDetailsService(EmployeeRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s)
            throws UsernameNotFoundException, DataAccessException {
        var user = userRepo.getEmployeeByEmail(s);
        var userDetails = new MyUserDetails(user);
        return userDetails;
    }
}
