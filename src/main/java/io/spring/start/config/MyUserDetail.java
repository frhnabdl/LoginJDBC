package io.spring.start.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.spring.start.models.User;
import io.spring.start.repositories.ManagementRepository;

// Kelas MyUserDetails adalah implementasi dari UserDetails interface dari Spring Security. 
// Kelas ini berfungsi untuk menyimpan informasi user yang diperlukan untuk proses autentikasi, 
// seperti username dan password.
@Service
public class MyUserDetail implements UserDetails, UserDetailsService {
    @Autowired
    private ManagementRepository managementRepository;
    private String username;
    private String password;
    private GrantedAuthority authority;

    public MyUserDetail() {}

    // parameter ganti dengan DTO Login -> karna buat get datanya
    public MyUserDetail(User user) {
        this.username = user.getEmployee().getEmail();
        this.password = user.getPassword();
        this.authority = new SimpleGrantedAuthority(user.getRole().getName());
    }

    // Implementasi method dari UserDetailsService
    // method ini untuk melakukan pengambilan informasi user dari database. 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // object data akan di passing ke constructor MyUSerDetails
        io.spring.start.models.User data = managementRepository.Login(username);
        return new MyUserDetail(data);
    }

    // Implementasi method dari UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(authority);
        return grantedAuthorities;
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
    
}
