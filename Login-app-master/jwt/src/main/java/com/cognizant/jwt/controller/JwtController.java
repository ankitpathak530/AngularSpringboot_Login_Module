package com.cognizant.jwt.controller;

import com.cognizant.jwt.helper.JwtUtil;
import com.cognizant.jwt.mdoel.JwtRequest;
import com.cognizant.jwt.mdoel.JwtResponse;
import com.cognizant.jwt.services.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class JwtController {

    private CustomUserDetailsService customUserDetailsService;
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (UsernameNotFoundException ex) {
            ex.printStackTrace();
            throw new Exception("Bad Credentials");
        }catch (BadCredentialsException ex){
            ex.printStackTrace();
            throw new Exception("Bad Credentials");
        }
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        System.out.println("JWT " + token);
        return ResponseEntity.ok().body(new JwtResponse(token,userDetails.getUsername(),userDetails.getPassword()));
    }

}
