package com.doNotWorry.security;

import com.doNotWorry.user.SiteUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User extends org.springframework.security.core.userdetails.User implements OAuth2User {
    public static User from(SiteUser siteUser) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("member"));

        return new User(siteUser, authorities);
    }

    private User(SiteUser siteUser, List<GrantedAuthority> authorities) {
        super(siteUser.getLoginID(), siteUser.getPassword(), authorities);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public String getName() {
        return getUsername();
    }

    public boolean hasAuthority(String authorityName) {
        return getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(authorityName));
    }
}