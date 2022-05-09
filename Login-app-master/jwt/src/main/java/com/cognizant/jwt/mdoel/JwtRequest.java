package com.cognizant.jwt.mdoel;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class JwtRequest {
    private String username;
    private String password;
}
