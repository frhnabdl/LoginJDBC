package io.spring.start.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.spring.start.models.User;
import io.spring.start.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> Get() {
        return userRepository.findAll();
    }

    @Override
    public User Get(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Data User Tidak Ditemukan"));
    }

    @Override
    public Boolean Save(User user) {
        userRepository.save(user);
        return userRepository.findById(user.getId()).isPresent();
    }        

    @Override
    public Boolean Delete(Integer id) {
        userRepository.deleteById(id);
        return !userRepository.findById(id).isPresent();
    }
    
}
