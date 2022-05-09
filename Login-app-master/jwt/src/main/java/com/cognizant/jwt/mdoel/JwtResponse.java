package com.cognizant.jwt.mdoel;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtResponse {
    private String token;
    private String username;
    private String password;
}
