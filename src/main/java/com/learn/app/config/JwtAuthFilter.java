package com.learn.app.config;

import com.learn.app.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("I am watching ur request");

        String accessToken = jwtUtil.resolveToken(request);

        // If valid access token not present then, don't proceed further
        if (accessToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println("token : "+accessToken);
        Claims claims = jwtUtil.resolveClaims(request);

        if (claims != null) {
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(claims.getSubject(),"",new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Forward the call to next filter chain
        filterChain.doFilter(request, response);
    }
}
