package miu.edu.myfinalproject.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Setter;
import miu.edu.myfinalproject.entity.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class MyUserDetails implements UserDetails {

    //    these three fields are custom and added by me
    private String username;
    @JsonIgnore
    private String password;
    private String role;

    public MyUserDetails(Employee user) {
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.role = "TRAVELER";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> rolesList = new ArrayList<>();
        rolesList.add(new SimpleGrantedAuthority(role));
        return rolesList;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "MyUserDetails{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
