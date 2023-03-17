package io.spring.start.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.spring.start.models.Role;
import io.spring.start.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    @Override
    public List<Role> Get() {
        return roleRepository.findAll();
    }

    @Override
    public Role Get(Integer id) {
        return roleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Data Region Tidak Ditemukan"));
    }

    @Override
    public Boolean Save(Role role) {
        roleRepository.save(role);
        return roleRepository.findById(role.getId()).isPresent();
    }

    @Override
    public Boolean Delete(Integer id) {
        roleRepository.deleteById(id);
        return !roleRepository.findById(id).isPresent();
    }
    
}
