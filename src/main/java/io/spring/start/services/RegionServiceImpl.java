package io.spring.start.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.spring.start.models.Region;
import io.spring.start.repositories.RegionRepository;

//REGION 4
//class ini yg menggunakan method dari region service (daftar isi)
@Service
public class RegionServiceImpl implements RegionService {
    private RegionRepository regionRepository; //memanggil class ini untuk menggunakn jpa crud database

    @Autowired
    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public List<Region> Get() {
        return regionRepository.findAll();
    }

    @Override
    public Region Get(Integer id) {
        return regionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Data Region Tidak Ditemukan"));
    }

    @Override
    public Boolean Save(Region region) {
        regionRepository.save(region);
        return regionRepository.findById(region.getId()).isPresent();
    }

    @Override
    public Boolean Delete(Integer id) {
        regionRepository.deleteById(id);
        return !regionRepository.findById(id).isPresent();
    }
    
}
