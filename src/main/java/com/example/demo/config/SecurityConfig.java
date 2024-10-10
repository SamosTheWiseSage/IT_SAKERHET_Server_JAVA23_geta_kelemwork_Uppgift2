package com.example.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.io.IOException;

import static com.example.demo.util.EncryptionUtil.secretKey;

@Configuration
public class SecurityConfig {
    private static final String SECRET_KEY = String.valueOf(secretKey); // Replace with your actual secret key
    private static final String AUTH_HEADER = "Authorization";


    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic if needed
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String authHeader = httpRequest.getHeader(AUTH_HEADER);

        // Check if the Authorization header is present and starts with "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Extract the token

            try {
                // Validate the token
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET_KEY)
                        .parseClaimsJws(token)
                        .getBody();

                // Optionally, set user info in request attributes for later use
                httpRequest.setAttribute("userEmail", claims.getSubject());

            } catch (SignatureException e) {
                httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                httpResponse.getWriter().write("Invalid token");
                return;
            } catch (Exception e) {
                httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                httpResponse.getWriter().write("Token validation failed");
                return;
            }
        } else {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpResponse.getWriter().write("Authorization header is missing or invalid");
            return;
        }

        chain.doFilter(request, response); // Continue with the filter chain
    }


    public void destroy() {
        // Cleanup logic if needed
    }

 /*    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

   @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER");
        return authenticationManagerBuilder.build();
    }
/*@Bean
public InMemoryUserDetailsManager userDetailsManager(){
    UserDetails user = User.withUsername("user")
            .password(passwordEncoder().encode("userPass"))
            .roles("USER")
            .build();
    return new InMemoryUserDetailsManager(user);
}


   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf
                        .ignoringRequestMatchers("/test-request","/api/**"))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/test-request","/api/**").authenticated()
                        .anyRequest().permitAll()
                ).httpBasic(Customizer.withDefaults());
        return http.build();
    }
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest()
            .permitAll());
    return http.build();

      @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.authorizeRequests(authorizeRequests -> authorizeRequests
                       .requestMatchers("api/register", "api/login","api/api").permitAll()
                       .anyRequest().authenticated())
                .csrf(csrf -> {
                    csrf.ignoringRequestMatchers("api/register", "api/login","api/api");
                });
        return http.build();
    }
}
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest()
            .permitAll())
            .csrf(AbstractHttpConfigurer::disable);
    return http.build();
}*/
}

