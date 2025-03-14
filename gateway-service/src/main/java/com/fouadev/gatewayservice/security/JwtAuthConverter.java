package com.fouadev.gatewayservice.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 Created by : Fouad SAIDI on 12/03/2025
 @author : Fouad SAIDI
 @date : 12/03/2025
 @project : bank-microservice-kafka
*/
@Component
public class JwtAuthConverter implements Converter<Jwt,AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        Collection<GrantedAuthority> authorities = Stream.concat(
                this.jwtGrantedAuthoritiesConverter.convert(source).stream(),
                extractResourceRoles(source).stream()
        ).collect(Collectors.toSet());
        return new JwtAuthenticationToken(source, authorities, source.getClaim("preferred_username"));
    }


    private Collection<GrantedAuthority> extractResourceRoles(Jwt jwt) {
        Map<String,Object> realmAccess;
        Collection<String> roles;
        if (jwt.getClaim("realm_access") == null) {
            return Set.of();
        }
        realmAccess = jwt.getClaim("realm_access");
        roles = (Collection<String>) realmAccess.get("roles");
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}