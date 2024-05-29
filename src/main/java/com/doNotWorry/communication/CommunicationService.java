package com.doNotWorry.communication;

import com.doNotWorry.user.SiteUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommunicationService {
    @Autowired
    private CommunicationRepository communicationRepository;
    public Communication create(SiteUser user, String title, String content) {
        Communication  communication=new Communication();

        communication.setTitle(title);
        communication.setContent(content);
        communication.setSiteUser(user);
        communication.setCreateDate(LocalDateTime.now());
        communicationRepository.save(communication);
        return communication;
    }
}
