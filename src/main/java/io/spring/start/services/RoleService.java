package io.spring.start.services;

import java.util.List;

import io.spring.start.models.Role;

public interface RoleService {
    public List<Role> Get(); 
    public Role Get(Integer id); 
    public Boolean Save(Role role); 
    public Boolean Delete(Integer id); 
}
