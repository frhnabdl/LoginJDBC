package io.spring.start.services;

import io.spring.start.models.Region;
import java.util.*;


//REGION 2
//interface ini sebagai daftar isi dari method yg akan digunakan
public interface RegionService{
    
    public List<Region> Get(); //READ -> method get all / select *
    public Region Get(Integer id); //READ -> method get by id / select where id -> untuk UPDATE
    public Boolean Save(Region region); //CREATE -> method insert data
    public Boolean Delete(Integer id); //DELETE -> method delete row

}