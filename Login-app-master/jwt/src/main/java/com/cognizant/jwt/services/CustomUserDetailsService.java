package com.cognizant.jwt.services;

import com.cognizant.jwt.mdoel.CustomUserDetails;
import com.cognizant.jwt.mdoel.UserEntity;
import com.cognizant.jwt.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //fetch data from database
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found!!");
        } else {
            return new CustomUserDetails(user);
        }

    }
}
