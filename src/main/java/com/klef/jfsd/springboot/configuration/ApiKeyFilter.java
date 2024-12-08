package com.klef.jfsd.springboot.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    private static final String API_KEY_HEADER = "api-key";

    private static final String VALID_API_KEY= "1234567890";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws  ServletException, IOException {

        String apiKey = request.getHeader(API_KEY_HEADER);

        if(request.getRequestURI().startsWith("/api/")){
            if (VALID_API_KEY.equals(apiKey)) {
                filterChain.doFilter(request, response);
            }else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid API key");
            }
        }else{
            filterChain.doFilter(request, response);

        }
    }
}
