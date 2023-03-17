package io.spring.start.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.spring.start.models.Department;
import io.spring.start.repositories.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }
    
    @Override
    public List<Department> Get() {
        return departmentRepository.findAll();
    }

    @Override
    public Department Get(Integer id) {
        return departmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Data Department Tidak Ditemukan"));
    }

    @Override
    public Boolean Save(Department department) {
        departmentRepository.save(department);
        return departmentRepository.findById(department.getId()).isPresent();
    }

    @Override
    public Boolean Delete(Integer id) {
        departmentRepository.deleteById(id);
        return !departmentRepository.findById(id).isPresent();
    }

    // // untuk ambil 1 kolom saja
    // @Override
    // public List<String> GetAllByRegion(Integer region_id) {
    //     return departmentRepository.GetAllByRegion(region_id);
    // }

    // untuk ambil 2/lebih kolom
    @Override
    public List<io.spring.start.dto.Department> GetAllByRegion(Integer region_id) {
        return departmentRepository.GetAllByRegion(region_id);
    }
    
}
