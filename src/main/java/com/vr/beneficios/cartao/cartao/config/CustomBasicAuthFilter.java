package com.vr.beneficios.cartao.cartao.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class CustomBasicAuthFilter extends OncePerRequestFilter {

    private static final int BASIC_LENGTH = 6;
    private static final String LOGIN = "username";
    private static final String SENHA = "password";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var headerAuthorization = request.getHeader("Authorization");

        if (headerAuthorization == null || !headerAuthorization.startsWith("Basic ")) {
            filterChain.doFilter(request, response);
            return;
        }
        var basicToken = headerAuthorization.substring(BASIC_LENGTH);
        byte[] basicTokenDecoded = Base64.getDecoder().decode(basicToken);

        String basicTokenValue = new String(basicTokenDecoded);

        String[] basicAuthsSplit = basicTokenValue.split(":");

        if (basicAuthsSplit[0].equals(LOGIN)
                && basicAuthsSplit[1].equals(SENHA)) {

            var authToken = new UsernamePasswordAuthenticationToken(basicAuthsSplit[0], null, null);
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);
    }
}
