package io.spring.start.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.spring.start.models.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    /*nulis sql bisa menggunakan framework dari jpa, seperti 
    
    public List<Department> Get(); //READ -> method get all / select *
    public Department Get(Integer id); //READ -> method get by id / select where id -> untuk UPDATE
    public Boolean Save(Department department); //CREATE -> method insert data
    public Boolean Delete(Integer id); //DELETE -> method delete row*/

    /*tapi jika ingin custom query bisa menggunakan:
    1. jpql -> mengambil nama kolom/atribut dari model
    2. query native -> mengambil nama kolom/atribut langsung dari database*/

    // contoh quey native
    // @Query(value = "SELECT * FROM tb_m_department d WHERE d.region_id = :reg_id", nativeQuery = true)

    // contoh jpql untuk ambil 1 kolom saja
    // @Query(value = "SELECT d.name FROM Department d WHERE d.region.id = :reg_id")
    // public List<String> GetAllByRegion(@Param("reg_id") Integer region_id); // dipanggil di department service Impl

    // contoh jpql untuk ambil 2/lebih kolom saja
    @Query(value = "SELECT new io.spring.start.dto.Department(d.id, d.name) FROM Department d WHERE d.region.id = :reg_id")
    public List<io.spring.start.dto.Department> GetAllByRegion(@Param("reg_id") Integer region_id); // dipanggil di department service Impl


}
