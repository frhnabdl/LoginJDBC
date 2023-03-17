package io.spring.start.dto;

// class ini digunakan sebagai transfer objek, ketika ingin menampilkan semua isi data dari beberapa kolom
public class Department {    
    private Integer id;
    private String name;

    public Department(Integer id, String name){
        this.id = id;
        this.name = name;
    }
    
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

}