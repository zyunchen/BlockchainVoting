package com.scu275.invoicemanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {


//        configuration.setAllowedOrigins(Arrays.asList("https://example.com"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST"));

        // 允许跨域访问的 URL
//        List<String> allowedOriginsUrl = new ArrayList<>();
//        allowedOriginsUrl.add("http://localhost:3000");
        CorsConfiguration config = new CorsConfiguration();
        //config.setAllowCredentials(true);
        // 设置允许跨域访问的 URL
//        config.setAllowedOrigins(allowedOriginsUrl);
        config.addAllowedOriginPattern("*");
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("### securityFilterChain enter");
//        http
//                .authorizeHttpRequests(requests -> requests
////                        .requestMatchers("/vertretungsplan").permitAll()
////                        .anyRequest().authenticated()
//                        .anyRequest().permitAll()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .permitAll());

        http.authorizeHttpRequests(a->{
//            a.requestMatchers("/api/v1/users/signup", "/api/v1/users/login","/login","/swagger-ui/*").permitAll();
//            a.anyRequest().authenticated();
            a.anyRequest().permitAll();
        });

        http.securityContext((securityContext) -> securityContext
			.requireExplicitSave(false)
		);

        http.formLogin(a->{
            a.permitAll();
            a.loginProcessingUrl("/login");
            a.usernameParameter("username").passwordParameter("password");
            a.successHandler(new LoginSuccessHandler());
            a.failureHandler(new LoginFailureHandler());
        });

        http.logout(a -> {
            a.permitAll();
            a.deleteCookies("JSESSIONID");
        });

        http.exceptionHandling(a->{
            a.accessDeniedHandler(new LoginAccessDeniedHandler());
            a.authenticationEntryPoint(new LoginEntryPoint());
        });

        http.cors(cors->cors.configurationSource(this.corsConfigurationSource()));


//        http.cors(a->{
//            a.disable();
//        });
        http.csrf(a->{
            a.disable();
        });





        return http.build();
    }
}
