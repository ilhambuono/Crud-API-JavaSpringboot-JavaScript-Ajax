package io.spring.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.spring.api.models.Region;
import io.spring.api.repositories.RegionRepository;

@Service
public class RegionServiceImpl implements RegionService{
    private RegionRepository regionRepository;

    @Autowired
    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public List<Region> Get(){
        return regionRepository.findAll();
       }
    
    @Override
    public Region Get(Integer id) {
            return regionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Data Region Tidak Ditemukan"));
        }

    @Override
    public Boolean save(Region region) {
        regionRepository.save(region);
        return regionRepository.findById(region.getId()).isPresent();
        
    }

    @Override
    public Boolean delete(Integer id) {
    regionRepository.deleteById(id);
    return !regionRepository.findById(id).isPresent();
    }   
}
