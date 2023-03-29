package io.spring.api.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.spring.api.dto.ResponseHandler;
import io.spring.api.models.Region;
import io.spring.api.repositories.RegionRepository;
import io.spring.api.services.RegionService;

@RestController
@RequestMapping("api")
public class RestRegionController {
    @Autowired
    private RegionService regionService;
    @Autowired
    private RegionRepository regionRepository;
    
    @Autowired
    public RestRegionController(RegionService regionService, RegionRepository regionRepository) {
        this.regionService = regionService;
        this.regionRepository = regionRepository;
    }
    
    // @GetMapping("region")
    // public ResponseEntity<List<Region>> Get(){
    //     List<Region> regions = new ArrayList<>();
    //     Region region = new Region();
    //     region.setId(1);
    //     region.setName("WIB");
    //     regions.add(region);

    //     region = new Region();
    //     region.setId(2);
    //     region.setName("WITA");
    //     regions.add(region);

    //     region = new Region();
    //     region.setId(1);
    //     region.setName("WIT");
    //     regions.add(region);
    //     return new ResponseEntity<>(regions, HttpStatus.OK);
    // }
    
    // @GetMapping("region/{id}")
    // public ResponseEntity<Region> Get(@PathVariable(required = true) Integer id){
    //     Region region = new Region();
    //     region.setId(1);
    //     region.setName("WIB");
    //     //return new ResponseEntity<>(region, HttpStatus.OK);
    //     return new ResponseEntity<>(region, HttpStatus.OK);
    // }
    @GetMapping("region")
    public ResponseEntity<Object> Get(){
    return ResponseHandler.getResponse("Data Ditemukan", HttpStatus.OK, regionService.Get());
       // return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }
    @GetMapping("region/{id}")
    public ResponseEntity<Object> Get(@PathVariable(required = true) Integer id){

        return ResponseHandler.getResponse("Data Ditemukan", HttpStatus.OK, regionService.Get(id));
    }
    @PostMapping("region")
    public ResponseEntity<Object> save(@RequestBody Region region){
        Boolean result = regionService.save(region);
        if (result){
            return ResponseHandler.generateResponse("Data Berhasil Disimpan", HttpStatus.OK);
        }
        return ResponseHandler.generateResponse("Data gagal Disimpan", HttpStatus.BAD_REQUEST);
    }
    @PutMapping("region/{id}")
    public ResponseEntity<Object> edit( @RequestBody Region region, @PathVariable(required = true) Integer id){   
        Region regionbyid = regionService.Get(id);
        // regionbyid.setId(region.getId());
        regionbyid.setName(region.getName());
        Boolean result = regionService.save(regionbyid);
        if (result){
            return ResponseHandler.generateResponse("Data Berhasil DiUpdate", HttpStatus.OK);
        }
        return ResponseHandler.generateResponse("Data Gagal DiUpdate", HttpStatus.OK);
    }
    
    @DeleteMapping("region/{id}")
    public ResponseEntity<Object> delete(@PathVariable(required = true) Integer id){
        
    Boolean result = regionService.delete(id);
    if(result){
        return ResponseHandler.generateResponse("Data Berhasil diHapus", HttpStatus.OK);
    }
    return ResponseHandler.generateResponse("Data gagal diHapus", HttpStatus.OK);
    }
        
}
