package io.spring.api.services;

import java.util.List;

import io.spring.api.models.Region;

public interface RegionService {
    public List<Region> Get();
    public Region Get(Integer id);
    public Boolean save(Region region);
    public Boolean delete(Integer id);

}
