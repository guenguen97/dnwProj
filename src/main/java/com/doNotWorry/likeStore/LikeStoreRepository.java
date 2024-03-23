package com.doNotWorry.likeStore;

import com.doNotWorry.user.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikeStoreRepository extends JpaRepository<LikeStore, Integer> {
    @Query("SELECT COUNT(ls) FROM LikeStore ls WHERE ls.foodDataId = :foodDataId AND ls.groupName = :groupName AND ls.siteUser = :siteUser")
    public Integer countLikeStore(@Param("foodDataId") Integer foodDataId, @Param("groupName") String groupName, @Param("siteUser") SiteUser user);
}
