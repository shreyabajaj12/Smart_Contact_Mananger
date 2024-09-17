package com.scm.services.impl;

import com.scm.entities.User;
import com.scm.helper.ResourceNotFoundException;
import com.scm.repositories.UserRepos;
import com.scm.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepos userRepos;

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        String userId= UUID.randomUUID().toString();
        user.setUserId(userId);

        return userRepos.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepos.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
       User user2= userRepos.findById(user.getUserId()).orElseThrow(()->new ResourceNotFoundException("user not found"));
       //update user2 from user
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setAbout(user.getAbout());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setProfilePic(user.getProfilePic());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());

        //save the user in the database
        User save=userRepos.save(user2);
        return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String id) {
        User user2= userRepos.findById(id).orElseThrow(()->new ResourceNotFoundException("user is not found"));
         userRepos.delete(user2);

    }

    @Override
    public boolean isUserExists(String id) {
        User user2=userRepos.findById(id).orElse(null);
        return user2!=null? true:false;
    }

    @Override
    public boolean isUserExistsByEmail(String email) {
        User user=userRepos.findByEmail(email).orElse(null);
        return user!=null? true:false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepos.findAll();
    }
}
