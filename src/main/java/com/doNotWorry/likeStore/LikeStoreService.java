package com.doNotWorry.likeStore;


import com.doNotWorry.user.SiteUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeStoreService {

    @Autowired
    private LikeStoreRepository likeStoreRepository;
    public void saveStore(SiteUser user, Integer id, String name) {
         LikeStore likeStore = new LikeStore();

         likeStore.setFoodDataId(id);
         likeStore.setName(name);
         likeStore.setSiteUser(user);
         likeStoreRepository.save(likeStore);



    }
}
