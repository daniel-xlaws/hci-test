package com.test.hci.service;

import com.test.hci.model.Module;
import com.test.hci.model.User;
import com.test.hci.model.UserModule;
import com.test.hci.repository.UserModuleRepo;
import com.test.hci.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserModuleRepo userModuleRepo;

    @Transactional
    public void insertUsers(User... users) {
        userRepo.saveAll(Arrays.asList(users));
    }

    @Transactional
    public void insertUserModules(UserModule ... userModules) {
        userModuleRepo.saveAll(Arrays.asList(userModules));
    }

    public List<UserModule> selectAllUserModules(User user) {
        return selectAllUserModules(user.getId());
    }

    public List<UserModule> selectAllUserModules(String userId) {
        return userModuleRepo.findAllByUserId(userId);
    }

    public User selectOneUserById(String id) {
        try {
            return userRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public UserModule selectOneUserModule(UserModule userModule) {
        return userModuleRepo.findByUserIdAndModuleNameAndModuleOrder(userModule.getUser().getId(), userModule.getModule().getName(), userModule.getModuleOrder());
    }
}
