package com.IP_geolocation.IP_geolocation.controllers;

import com.IP_geolocation.IP_geolocation.model.Geolocation;
import com.IP_geolocation.IP_geolocation.repositories.GeolocationRepository;
import com.IP_geolocation.IP_geolocation.services.GeolocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class GeolocationController {
    @Autowired
    //private GeolocationRepository geolocationRepository;
    private GeolocationService geolocationService;

    @GetMapping("/geolocations")
    public List<Geolocation> getallGeolocations(){
        return geolocationService.findAll();
    }

    @PostMapping("/geolocations")
    public Geolocation createGeolocation(@RequestBody String ip) throws Exception{

        return geolocationService.createLocation(ip);

    }

    @GetMapping("/geolocations/{ip}")
    public ResponseEntity<Geolocation> getGeolocationbyIp(@PathVariable String ip){
        Geolocation geolocation = geolocationService.getLocation(ip);
                //.orElseThrow(()->new Exception("ip location not exist with id: " + ip));
        return ResponseEntity.ok(geolocation);
    }

}
