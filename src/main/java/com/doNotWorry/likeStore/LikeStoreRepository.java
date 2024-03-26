package com.doNotWorry.likeStore;

import com.doNotWorry.user.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.LinkedHashSet;
import java.util.List;

public interface LikeStoreRepository extends JpaRepository<LikeStore, Integer> {
    @Query("SELECT COUNT(ls) FROM LikeStore ls WHERE ls.foodDataId = :foodDataId AND ls.groupName = :groupName AND ls.siteUser = :siteUser")
    public Integer countLikeStore(@Param("foodDataId") Integer foodDataId, @Param("groupName") String groupName, @Param("siteUser") SiteUser user);

    List<LikeStore> findBySiteUser(SiteUser user);


    @Query("SELECT ls.name FROM LikeStore ls WHERE ls.groupName = :groupName AND  ls.siteUser = :user")

    List<String> findByGroupName(@Param("groupName") String groupName, @Param("user") SiteUser user);
}
