package com.doNotWorry.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  UserRepository  extends JpaRepository<SiteUser, Integer> {
    Optional<SiteUser> findByuserName(String username);

    Optional<SiteUser> findByLoginID(String loginID);
}
