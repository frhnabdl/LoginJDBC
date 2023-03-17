package io.spring.start.services;

import java.util.List;

import io.spring.start.models.User;

public interface UserService {
    public List<User> Get(); //READ -> method get all / select *
    public User Get(Integer id); //READ -> method get by id / select where id -> untuk UPDATE
    public Boolean Save(User user); //CREATE -> method insert data
    public Boolean Delete(Integer id); //DELETE -> method delete row
}
