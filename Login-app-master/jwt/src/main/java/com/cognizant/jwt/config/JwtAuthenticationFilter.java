package com.cognizant.jwt.config;

import com.cognizant.jwt.helper.JwtUtil;
import com.cognizant.jwt.services.CustomUserDetailsService;
import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;


@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
    private JwtUtil jwtUtil;
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //get jwt
        String requestHeader = request.getHeader("Authorization");
        
        String username = null;
        String jwtToken = null;

        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            jwtToken = requestHeader.substring(7);
            
            try {
                username = jwtUtil.extractUsername(jwtToken);
            } catch (Exception e) {
                e.printStackTrace();
            }
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else{
                System.out.println("Token is not validated..");
            }
        }else{
            System.out.println("Token Generated successfully...");

        }
        //start with bearer
        //validate

        filterChain.doFilter(request,response);
    }

	
    
    
}
