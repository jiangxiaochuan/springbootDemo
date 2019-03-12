package com.sanjin.configuration.shiro.token;

import lombok.*;
import org.apache.shiro.authc.AuthenticationToken;


/**
 * @description: shiro jwtToken
 * @author: sanjin
 * @date: 2019.3.12
 */
@Data
public class JwtToken implements AuthenticationToken {
    private String token;

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
