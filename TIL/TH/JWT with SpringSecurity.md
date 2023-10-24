# Spring Security + JWT

1. SecurityConfig 설정
    - 시큐리티의 기본적인 설정을 하는 클래스
    - 인증이 필요 없는 URL을 설정할 수 있음
    
    ```java
    @Configuration
    public class SecurityConfig {
        private final String[] allowedUrls = {"/", "/swagger-ui/**", "/v3/**", "/sign-up", "/sign-in"};	// sign-up, sign-in 추가
     
        @Bean
        public SecurityFilterChain filterChain(Http httpSecurity) throws Exception {
            return http
                    .csrf().disable()
                    .headers(headers -> headers.frameOptions().sameOrigin())
                    .authorizeHttpRequests(requests ->
                            requests.requestMatchers(allowedUrls).permitAll()	// 허용할 url 목록을 배열로 분리했다
                                    .requestMatchers(PathRequest.toH2Console()).permitAll()
                                    .anyRequest().authenticated()
                    )
                    .sessionManagement(sessionManagement ->
                            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    )
                    .build();
        }
    }
    ```
    
2. 비밀번호 암호화 저장
    - DB에 비밀번호를 암호화 해서 저장하는거도 시큐리티가 해줌!!
    
    ```java
    @Configuration
    public class SecurityConfig {
        ...
        
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }
    ```
    
3. 암호화 적용
    - 암호화 하는 함수를 만들었으니 회원가입, 로그인 단계에서 암호화된 비밀번호를 사용해야 함
    
    ```java
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    @Getter
    @Entity
    public class Member {
        ...
     
        public static Member from(SignUpRequest request, PasswordEncoder encoder) {	// 파라미터에 PasswordEncoder 추가
            return Member.builder()
                    .account(request.account())
                    .password(encoder.encode(request.password()))	// 수정
                    .name(request.name())
                    .age(request.age())
                    .type(MemberType.USER)
                    .createdAt(LocalDateTime.now())
                    .build();
        }
     
        public void update(MemberUpdateRequest newMember, PasswordEncoder encoder) {	// 파라미터에 PasswordEncoder 추가
            this.password = newMember.newPassword() == null || newMember.newPassword().isBlank() 
                    ? this.password : encoder.encode(newMember.newPassword());	// 수정
            this.name = newMember.name();
            this.age = newMember.age();
        }
    }
    ```
    
    - 사실 굳이 이렇게 안 하고 하던데로 setter에서 해도 되지만 일단 시큐리티를 배우는 단계이니 최대한 시큐리티를 사용하자!!
        
        ⇒ 이건 선호하는 방식으로 하면 됨
        
4. 서비스 코드에도 암호화를 적용
    
    ```java
    @RequiredArgsConstructor
    @Service
    public class SignService {
        private final MemberRepository memberRepository;
        private final TokenProvider tokenProvider;
        private final PasswordEncoder encoder;	// 추가
     
        @Transactional
        public SignUpResponse registMember(SignUpRequest request) {
            Member member = memberRepository.save(Member.from(request, encoder));	// 회원가입 정보를 암호화하도록 수정
            try {
                memberRepository.flush();
            } catch (DataIntegrityViolationException e) {
                throw new IllegalArgumentException("이미 사용중인 아이디입니다.");
            }
            return SignUpResponse.from(member);
        }
     
        @Transactional(readOnly = true)
        public SignInResponse signIn(SignInRequest request) {
            Member member = memberRepository.findByAccount(request.account())
                    .filter(it -> encoder.matches(request.password(), it.getPassword()))	// 암호화된 비밀번호와 비교하도록 수정
                    .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));
            String token = tokenProvider.createToken(String.format("%s:%s", member.getId(), member.getType()));
            return new SignInResponse(member.getName(), member.getType(), token);
        }
    }
    ```
    
    ```java
    @RequiredArgsConstructor
    @Service
    public class MemberService {
        private final MemberRepository memberRepository;
        private final PasswordEncoder encoder;	// 추가
     
        ...
     
        @Transactional
        public MemberUpdateResponse updateMember(UUID id, MemberUpdateRequest request) {
            return memberRepository.findById(id)
                    .filter(member -> encoder.matches(request.password(), member.getPassword()))	// 암호화된 비밀번호와 비교하도록 수정
                    .map(member -> {
                        member.update(request, encoder);	// 새 비밀번호를 암호화하도록 수정
                        return MemberUpdateResponse.of(true, member);
                    })
                    .orElseThrow(() -> new NoSuchElementException("아이디 또는 비밀번호가 일치하지 않습니다."));
        }
    }
    ```
    
    - SignService에서는 Config에서 설정한 PasswordEncoder 빈을 주입받아서 사용
        - 만약 다른 암호화 모듈을 사용하게 된다면 Config의 PasswordEncoder 빈만 다른 암호화 모듈로 교체하면 됨!!
5. 권한 부여(with. JWT)
    - JWT를 사용해서 Bearer 토큰으로 권한 확인할거얌
    - 먼저 의존성 추가!!
    
    ```java
    dependencies {
        ...
        implementation("io.jsonwebtoken:jjwt-api:0.11.5")
        runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
        runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
        ...
    }
    ```
    
6. 로그인 하면 토큰 발급하게 하기
    - JWT를 사용하기위해 비밀키가 필요함
        
        ⇒ jwt.yml파일 생성
        
        ```java
        secret-key: NiOeyFbN1Gqo10bPgUyTFsRMkJpGLXSvGP04eFqj5B30r5TcrtlSXfQ7TndvYjNvfkEKLqILn0j1SmKODO1Yw3JpBBgI3nVPEahqxeY8qbPSFGyzyEVxnl4AQcrnVneI
        expiration-hours: 3
        issuer: colabear754
        ```
        
        - secret-key : JWT 암호화에 사용할 비밀키. 여기서는 HS512 암호화 알고리즘을 사용하기 때문에 512비트(64바이트) 이상의 크기인 비밀키 사용
        - expiration-hours : 발급된 토큰의 만료시간. 토큰이 발급되고 설정된 시간이 지나면 해당 토큰은 무효화 됨
7. TokenProvider 작성
    - 토큰을 생성해줄 클래스
    
    ```java
    @PropertySource("classpath:jwt.yml")
    @Service
    public class TokenProvider {
        private final String secretKey;
        private final long expirationHours;
        private final String issuer;
     
        public TokenProvider(
                @Value("${secret-key}") String secretKey,
                @Value("${expiration-hours}") long expirationHours,
                @Value("${issuer}") String issuer
        ) {
            this.secretKey = secretKey;
            this.expirationHours = expirationHours;
            this.issuer = issuer;
        }
     
        public String createToken(String userSpecification) {
            return Jwts.builder()
                    .signWith(new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName()))   // HS512 알고리즘을 사용하여 secretKey를 이용해 서명
                    .setSubject(userSpecification)  // JWT 토큰 제목
                    .setIssuer(issuer)  // JWT 토큰 발급자
                    .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))    // JWT 토큰 발급 시간
                    .setExpiration(Date.from(Instant.now().plus(expirationHours, ChronoUnit.HOURS)))    // JWT 토큰 만료 시간
                    .compact(); // JWT 토큰 생성
        }
    }
    ```
    
8. 토큰을 생성했으니 토큰을 발급해야지!!
    - 로그인 할 때 발급할 거니까 SignInResponse, SignService의 signIn() 메서드 수정
    
    ```java
    public record SignInResponse(
            @Schema(description = "회원 이름", example = "콜라곰")
            String name,
            @Schema(description = "회원 유형", example = "USER")
            MemberType type,
            String token	// 추가
    ) {
    }
    ```
    
    ```java
    @RequiredArgsConstructor
    @Service
    public class SignService {
        private final MemberRepository memberRepository;
        private final TokenProvider tokenProvider;	// 추가
        
        ...
        
        @Transactional(readOnly = true)
        public SignInResponse signIn(SignInRequest request) {
            Member member = memberRepository.findByAccount(request.account())
                    .filter(it -> it.getPassword().equals(request.password()))
                    .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));
            String token = tokenProvider.createToken(String.format("%s:%s", member.getId(), member.getType()));	// 토큰 생성
            return new SignInResponse(member.getName(), member.getType(), token);	// 생성자에 토큰 추가
        }
    }
    ```
    
9. 토큰을 발급 받았으니 Swagger에서 사용을 해볼까??
    
    ```java
    @Configuration
    public class SwaggerConfig {
        private static final String SECURITY_SCHEME_NAME = "authorization";	// 추가
     
        @Bean
        public OpenAPI swaggerApi() {
            return new OpenAPI()
                    .components(new Components()
                    // 여기부터 추가 부분
                            .addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme()
                                    .name(SECURITY_SCHEME_NAME)
                                    .type(SecurityScheme.Type.HTTP)
                                    .scheme("bearer")
                                    .bearerFormat("JWT")))
                    .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                    // 여기까지
                    .info(new Info()
                            .title("스프링시큐리티 + JWT 예제")
                            .description("스프링시큐리티와 JWT를 이용한 사용자 인증 예제입니다.")
                            .version("1.0.0"));
        }
    }
    ```
    
    - addSecuritySchemes() : 인증 정보 입력을 위한 버튼 생성
    - addSecurityItem() : 시큐리티 요구 사항을 스웨거에 추가
    - SECURITY_SCHEME_NAME : 시큐리티 스키마의 이름을 뜻함
        
        ⇒ API 호출을 위해 HTTP요청을 보낼 때 Authorization 헤더에 JWT 기반의 Bearer 토큰을 사용할 것이기 때문에 시큐리티 스키마는 위와 같이 설정
        
10. 이제 설정까지 맞추었으니 토큰을 활용해보자!!
    
    ```java
    @PropertySource("classpath:jwt.yml")
    @Service
    public class TokenProvider {
        ...
        
        public String validateTokenAndGetSubject(String token) {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }
    }
    ```
    
    - validateTokenAndGetSubject() : 비밀키를 토대로 createToken()에서 토큰에 담은 Subject를 복호화 하여 문자열 형태로 반환하는 메서드
        - Subject에는 SignService의 signIn()에서 토큰을 생성할 때 인자로 넘긴 “{회원ID}:{회원타입}”이 담겨있음
11. 이제 이거로 권한을 구분해보자!!
    
    ```java
    @Order(0)
    @RequiredArgsConstructor
    @Component
    public class JwtAuthenticationFilter extends OncePerRequestFilter {
        private final TokenProvider tokenProvider;
     
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String token = parseBearerToken(request);
            User user = parseUserSpecification(token);
            AbstractAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(user, token, user.getAuthorities());
            authenticated.setDetails(new WebAuthenticationDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticated);
     
            filterChain.doFilter(request, response);
        }
     
        private String parseBearerToken(HttpServletRequest request) {
            return Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                    .filter(token -> token.substring(0, 7).equalsIgnoreCase("Bearer "))
                    .map(token -> token.substring(7))
                    .orElse(null);
        }
     
        private User parseUserSpecification(String token) {
            String[] split = Optional.ofNullable(token)
                    .filter(subject -> subject.length() >= 10)
                    .map(tokenProvider::validateTokenAndGetSubject)
                    .orElse("anonymous:anonymous")
                    .split(":");
     
            return new User(split[0], "", List.of(new SimpleGrantedAuthority(split[1])));
        }
    }
    ```
    
    - @Order : int범위 내에서 의존성 주입 순위를 설정(수치가 낮을수록 높음). 이 때 우선순위를 너무 높이면 유효한 토큰이라고 인증이 안되며, 너무 낮으면 토큰이 없어도 통과됨
    - parseBearerToken() : HTTP요청 헤더에서 Authorization값을 찾아서 Bearer로 시작하는지 확인. 접두어를 제외한 토큰값으로 파싱함. 헤더에 Authorization이 존재하지 않거나 접두어가 Bearer이 아니면 null 반환
    - parseUserSpecification() : 토큰값을 토대로 토큰에 담긴 정보(id,type)로 시큐리티에서 사용한 User 객체를 반환. 이 때 파싱된 토큰이 null이 아니면서 길이가 너무 짧지 않을 때만 토큰을 복호화하며, 그 외에는 익명을 뜻하는 User객체를 생성. 비밀번호는 로그인 API를 호출할 때 이미 확인했기 때문에 User객체를 생성할 때는 사용하지 않음으로 빈 문자열 넘김
    - doFilterInternal() : 인증정보를 설정. HTTP요청 헤더의 토큰을 기반으로 생성한 User 객체를 토대로 스프링 시큐리티에서 사용할 UsernamePasswordAuthenticationToken 객체를 생성. 이후 스프링 시큐리티 컨텍스트의 인증 정보를 새로 생성한 인증 토큰으로 설정하고 다음 필터로 넘어감
        - 인증 토큰의 details에는 요청을 날린 클라이언트 또는 프록시의 IP주소와 세션ID를 저장함. 필요하지 않다면 삭제 해도 됨
12. 이렇게 멋있게 필터를 만들어줬으니 적용!!
    - SecurityFilterChain 설정에 새로 작성한 JwtAuthenticationFilter를 추가해야 함
    - 마지막으로 @EnableMethodSecurity를 추가하여 메소드 시큐리티 활성화
    
    ```java
    @EnableMethodSecurity	// 추가
    @RequiredArgsConstructor
    @Configuration
    public class SecurityConfig {
        private final JwtAuthenticationFilter jwtAuthenticationFilter;	// JwtAuthenticationFilter 주입
     
        private final String[] allowedUrls = {"/", "/swagger-ui/**", "/v3/**"};
     
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            return http
                    .csrf().disable()
                    .headers(headers -> headers.frameOptions().sameOrigin())
                    .authorizeHttpRequests(requests ->
                            requests.requestMatchers(allowedUrls).permitAll()
                                    .requestMatchers(PathRequest.toH2Console()).permitAll()
                                    .anyRequest().authenticated()
                    )
                    .sessionManagement(sessionManagement ->
                            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    )
                    .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class)	// 추가
                    .build();
        }
     
        ...
    }
    ```
    
13. 오케이 이제 권한까지는 확인 했고 접근을 막아보자!!
    - 이제 API에 권한별로 제한을 걸거얌
    - Controller에 @PreAuthorize로 제한을 걸 수 있당
    
    ```java
    @Tag(name = "관리자용 API")
    @RequiredArgsConstructor
    @PreAuthorize("hasAuthority('ADMIN')")	// 추가
    @RestController
    @RequestMapping("/admin")
    public class AdminController {
        ...
    }
    ```
    
    ```java
    @Tag(name = "로그인 후 사용할 수 있는 API")
    @RequiredArgsConstructor
    @PreAuthorize("hasAuthority('USER')")	// 추가
    @RestController
    @RequestMapping("/member")
    public class MemberController {
        ...
    }
    ```
    
14. 진짜 마지막!!
    - 회원들은 본인의 정보에만 접근 가능하도록 해야한다!!
    
    ```java
    @Tag(name = "로그인 후 사용할 수 있는 API")
    @RequiredArgsConstructor
    @UserAuthorize
    @RestController
    @RequestMapping("/member")
    public class MemberController {
        private final MemberService memberService;
     
        @Operation(summary = "회원 정보 조회")
        @GetMapping
        public ApiResponse getMemberInfo(@AuthenticationPrincipal User user) {
            return ApiResponse.success(memberService.getMemberInfo(UUID.fromString(user.getUsername())));
        }
     
        @Operation(summary = "회원 탈퇴")
        @DeleteMapping
        public ApiResponse deleteMember(@AuthenticationPrincipal User user) {
            return ApiResponse.success(memberService.deleteMember(UUID.fromString(user.getUsername())));
        }
     
        @Operation(summary = "회원 정보 수정")
        @PutMapping
        public ApiResponse updateMember(@AuthenticationPrincipal User user, @RequestBody MemberUpdateRequest request) {
            return ApiResponse.success(memberService.updateMember(UUID.fromString(user.getUsername()), request));
        }
    }
    ```
    
    - 기존 문자열 형태로 받던 파라미터 ⇒ 스프링 시큐리티의 User객체로 변환
    - MemberService의 메서드를 호출할 때 넘겨주던 ID인자 ⇒ User객체의 username을 넘겨주도록 변경
        - User 객체 : 앞에서 JwtAuthenticationFilter에서 토큰을 토대로 생성하여 시큐리티 컨텍스트에 인증 정보로 설정한 그 User 객체