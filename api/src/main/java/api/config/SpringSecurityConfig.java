package api.config;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import api.security.jwt.JwtAuthenticationEntryPoint;
import api.security.jwt.JwtAuthorizationFilter;

@EnableMethodSecurity
@EnableWebMvc
@Configuration
public class SpringSecurityConfig {
	
	private static final String[] DOCUMENTATION_OPENAPI = {
	        "/docs/index.html",
	        "/docs-estacionamento.html", "/docs-estacionamento/**",
	        "/v3/api-docs/**",
	        "/swagger-ui-custom.html", "/swagger-ui.html", "/swagger-ui/**",
	        "/**.html", "/webjars/**", "/configuration/**", "/swagger-resources/**"
	};
	
	

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .authorizeHttpRequests(auth -> auth
                		.requestMatchers(antMatcher(HttpMethod.POST, "/api/v1/usuarios")).permitAll() 
                		.requestMatchers(antMatcher(HttpMethod.POST, "/api/v1/auth")).permitAll()
                		.requestMatchers(DOCUMENTATION_OPENAPI).permitAll()
                		.anyRequest().authenticated() 
                ).sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).addFilterBefore(
                        jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class
                ).exceptionHandling(ex -> ex
                        .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                ).build();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
