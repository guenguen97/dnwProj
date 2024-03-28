package com.doNotWorry.likeStore;


import com.doNotWorry.user.SiteUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;


@Service
public class LikeStoreService {

    @Autowired
    private LikeStoreRepository likeStoreRepository;
    public void saveStore(SiteUser user, Integer id, String name,String groupName) {
         LikeStore likeStore = new LikeStore();

         likeStore.setFoodDataId(id);
         likeStore.setName(name);
         likeStore.setSiteUser(user);
         likeStore.setGroupName(groupName);
         likeStoreRepository.save(likeStore);



    }

    public Integer countLikeStore(SiteUser user ,Integer foodDataId, String groupName) {
       return likeStoreRepository.findByFoodDataIdAndGroupNameAndSiteUser(foodDataId, groupName ,user ).size();
    }

    public List<LikeStore> likeStoreByFoodDataIdAndGroupNameAndSiteUser(SiteUser user ,Integer foodDataId, String groupName){
        return likeStoreRepository.findByFoodDataIdAndGroupNameAndSiteUser(foodDataId, groupName ,user );

    }

    public LinkedHashSet<String> findGroupByUser(SiteUser user) {
        List<LikeStore> likeStoreList = likeStoreRepository.findBySiteUser(user);
        LinkedHashSet<String> list = new LinkedHashSet<>();

        for (int i = 0; i < likeStoreList.size(); i++) {
            list.add(likeStoreList.get(i).getGroupName());
        }
        if(!list.contains("맛집") || !list.contains("가보고 싶은 곳")|| !list.contains("기타") ){
            list.add("맛집");
            list.add("가보고 싶은 곳");
            list.add("기타");
        }



        return list;

    }

    public List<String> findStoreByGroupName(String groupName,SiteUser user) {
        return likeStoreRepository.findByGroupName(groupName, user);
    }

    public List<LikeStore> findBySiteUserAndFoodDataId(SiteUser user, Integer id) {
        return likeStoreRepository.findBySiteUserAndFoodDataId(user, id);
    }

    public void deleteByFoodDataIdAndGroupNameAndSiteUser(SiteUser user, Integer id, String groupName) {
        likeStoreRepository.deleteByFoodDataIdAndGroupNameAndSiteUser( id , groupName ,user);
    }
}
