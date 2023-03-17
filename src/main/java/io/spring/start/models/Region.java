package io.spring.start.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//REGION 1
//class ini sebagai definisi dari table database & untuk akses datanya
@Entity
@Table(name = "tb_m_region")
public class Region {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY) // untuk autoincrement
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    // penghubung FK
    @OneToMany(mappedBy = "region") //buat ngarahin ke department yg ada nama regionnya (optional) tapi disarankan
    private Set<Department> departments;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }
    

}
