package com.doNotWorry.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRepository  extends JpaRepository<SiteUser, Integer> {

}
