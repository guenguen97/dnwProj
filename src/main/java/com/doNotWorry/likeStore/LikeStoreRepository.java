package com.doNotWorry.likeStore;

import com.doNotWorry.user.SiteUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.LinkedHashSet;
import java.util.List;

public interface LikeStoreRepository extends JpaRepository<LikeStore, Integer> {
//    @Query("SELECT FROM LikeStore ls WHERE ls.foodDataId = :foodDataId AND ls.groupName = :groupName AND ls.siteUser = :siteUser")
    List<LikeStore> findByFoodDataIdAndGroupNameAndSiteUser(@Param("foodDataId") Integer foodDataId, @Param("groupName") String groupName, @Param("siteUser") SiteUser user);

    @Transactional
    void deleteByFoodDataIdAndGroupNameAndSiteUser(@Param("foodDataId") Integer foodDataId, @Param("groupName") String groupName, @Param("siteUser") SiteUser user);

    List<LikeStore> findBySiteUser(SiteUser user);

    List<LikeStore> findBySiteUserAndFoodDataId(SiteUser user,Integer id);


    @Query("SELECT ls.name FROM LikeStore ls WHERE ls.groupName = :groupName AND  ls.siteUser = :user")

    List<String> findByGroupName(@Param("groupName") String groupName, @Param("user") SiteUser user);
}
