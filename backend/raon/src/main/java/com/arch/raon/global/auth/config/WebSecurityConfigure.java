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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

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
                .cors(cors ->
                        cors
                                .configurationSource(corsConfigurationSource()))
                .headers((headerConfig) ->
                        headerConfig.cacheControl(HeadersConfigurer.CacheControlConfig::disable)
                                .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(httpBasic -> httpBasic.disable());

        //요청에 대한 권한 설정
        http.authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
//                        .requestMatchers(PathRequest.toH2Console()).permitAll()
//                        .requestMatchers("/", "/login/**").permitAll()
//                        .requestMatchers("/posts/**", "/api/v1/posts/**").hasRole(Role.USER.name())
//                        .requestMatchers("/admins/**", "/api/v1/admin/**").hasRole(Role.ADMIN.name())
                                // TODO: 권한 없이도 접근 허가 -> requestMatchers 설정하기
                                .requestMatchers("/**").permitAll()
                                .anyRequest().authenticated()

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
                .logoutSuccessUrl("/")
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
        );

        //jwt filter 설정
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        System.out.println("http = " + http);
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000","https://arch-raon.com"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
