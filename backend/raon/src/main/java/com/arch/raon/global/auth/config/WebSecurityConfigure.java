package com.arch.raon.global.auth.config;

import com.arch.raon.global.auth.jwt.JwtAuthenticationFilter;
import com.arch.raon.global.auth.jwt.JwtTokenProvider;
import com.arch.raon.global.auth.oauth2.OAuth2AuthenticationFailureHandler;
import com.arch.raon.global.auth.oauth2.OAuth2AuthenticationSuccessHandler;
import com.arch.raon.global.auth.repository.CookieAuthorizationRequestRepository;
import com.arch.raon.global.auth.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfigure {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final JwtTokenProvider jwtTokenProvider;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("http = " + http);

        //httpBasic, csrf, formLogin, rememberMe, logout, session disable
        http
                .csrf(AbstractHttpConfigurer::disable
                )
                .headers((headerConfig) ->
                        headerConfig.cacheControl(HeadersConfigurer.CacheControlConfig::disable)
                                .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                )
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable);

        //요청에 대한 권한 설정
        http.authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
//                        .requestMatchers(PathRequest.toH2Console()).permitAll()
//                        .requestMatchers("/", "/login/**").permitAll()
//                        .requestMatchers("/posts/**", "/api/v1/posts/**").hasRole(Role.USER.name())
//                        .requestMatchers("/admins/**", "/api/v1/admin/**").hasRole(Role.ADMIN.name())
                                .anyRequest().permitAll()
        );

        //oauth2Login
        http.oauth2Login(oauth2Login -> oauth2Login
                .authorizationEndpoint(authorization -> authorization
//                         .baseUri("/oauth2/authorization")
                                .authorizationRequestRepository(cookieAuthorizationRequestRepository)
                )
//                .redirectionEndpoint(redirection -> redirection
//                        .baseUri("/login/oauth2/code/kakao") // 소셜 인증 후 redirect url
//                )
                //userService()는 OAuth2 인증 과정에서 Authentication 생성에 필요한 OAuth2User 를 반환하는 클래스를 지정한다.
                .userInfoEndpoint(userInfo -> userInfo
                        .userService(customOAuth2UserService)  // 회원 정보 처리
                )
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler));

        http.logout(logout -> logout
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
        );

        //jwt filter 설정
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        System.out.println("http = " + http);
        return http.build();
    }
}
