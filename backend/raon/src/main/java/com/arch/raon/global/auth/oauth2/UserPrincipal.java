package com.arch.raon.global.auth.oauth2;

import com.arch.raon.domain.member.entity.Member;
import com.arch.raon.global.auth.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class UserPrincipal implements OAuth2User, UserDetails {

    private Long id;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    @Setter
    private Map<String, Object> attributes;

    public UserPrincipal(Long id, String email, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.authorities = authorities;
    }

    /**
     * 해당 User의 권한 리턴
     * @param member
     * @param attributes
     * @return
     */
    public static UserPrincipal create(Member member, Map<String, Object> attributes) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(RoleType.USER.name()));
        UserPrincipal userPrincipal = new UserPrincipal(member.getId(), member.getEmail(), authorities);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }

    @Override
    public String getUsername() {
        return email;
    }
    // 계정 만료 여부
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    // 계정 잠겼는지
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    // 비밀번호 기간 지났는지
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    // 활성화 여부
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getName() {
        return String.valueOf(id);
    }
}