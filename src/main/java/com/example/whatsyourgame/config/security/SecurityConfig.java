package com.example.whatsyourgame.config.security;

import com.example.whatsyourgame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .headers().frameOptions().disable();

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

        http
                .authorizeRequests(request->
//                                request.antMatchers("/**").permitAll()
                                request
                                        .antMatchers("/", "/css/**", "/fonts/**", "/images/**", "/js/**", "/sass/**",
                                                "/favicon.ico", "/resources/**", "/error").permitAll()
                                        .antMatchers("/users", "/users/emailDuplicateCheck", "/users/login", "/users/login-error").permitAll()
                                        .antMatchers("/games/**", "/reviews/**").permitAll()
                                        .antMatchers("/users/mypage").hasRole("USER")

                                .anyRequest().authenticated()
                )
                .formLogin(login->
                        login.loginPage("/users")
                                .loginProcessingUrl("/users/login")
                                .defaultSuccessUrl("/", false)
                                .failureUrl("/users/login-error")
                )
                .logout(logout->
                        logout.logoutUrl("/users/logout")
                                .logoutSuccessUrl("/")
                )
                .exceptionHandling(error->
                        error.accessDeniedPage("/users")
                )
                .oauth2Login(oauth->
                        oauth.userInfoEndpoint()
                                .userService(customOAuth2UserService)
                )
        ;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .requestMatchers(
                        PathRequest.toStaticResources().atCommonLocations()
                )
        ;
    }

    // password encoding
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
