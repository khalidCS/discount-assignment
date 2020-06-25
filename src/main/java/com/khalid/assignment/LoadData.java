package com.khalid.assignment;

import com.khalid.assignment.bean.User;
import com.khalid.assignment.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class LoadData {

    private final UserRepository repository;

    public LoadData(UserRepository repository) {
        this.repository = repository;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        loadUsers();
    }

    private void loadUsers() {
        repository.save(loadUser("khalid", "employee", Instant.now()));
        repository.save(loadUser("eyad", "affiliate", Instant.now()));
    }

    private User loadUser(String userName, String role, Instant createdAt) {
        User user = new User();
        user.setUserName(userName);
        user.setCreatedAt(createdAt);
        user.setRole(role);
        return user;
    }

}
