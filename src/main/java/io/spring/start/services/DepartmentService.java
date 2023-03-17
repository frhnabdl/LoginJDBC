package io.spring.start.services;

import java.util.List;

import io.spring.start.models.Department;

public interface DepartmentService {
    public List<Department> Get(); //READ -> method get all / select *
    public Department Get(Integer id); //READ -> method get by id / select where id -> untuk UPDATE
    public Boolean Save(Department department); //CREATE -> method insert data
    public Boolean Delete(Integer id); //DELETE -> method delete row

    // //tambahan untuk ambil 1 kolom saja
    // public List<String> GetAllByRegion(Integer region_id); // dipanggil di department service Impl

    // //tambahan untuk ambil semua kolom
    // public List<Department> GetAllByRegion(Integer region_id); // dipanggil di department service Impl

    //tambahan untuk ambil 2/lebih kolom saja
    public List<io.spring.start.dto.Department> GetAllByRegion(Integer region_id); // dipanggil di department service Impl
}
