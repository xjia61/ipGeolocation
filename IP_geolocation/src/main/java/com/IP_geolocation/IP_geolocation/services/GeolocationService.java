package com.IP_geolocation.IP_geolocation.services;

import com.IP_geolocation.IP_geolocation.model.Geolocation;
import com.IP_geolocation.IP_geolocation.repositories.GeolocationRepository;
import com.maxmind.db.InvalidDatabaseException;
import com.maxmind.db.Reader;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.sun.xml.internal.rngom.digested.DAnnotation;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Configuration
public class GeolocationService {
    private static DatabaseReader reader = null;
    private GeolocationRepository geolocationRepository;
    private static Map<Geolocation, Long> timeMap = new ConcurrentHashMap<Geolocation, Long>();
    //private static Set<String> ipIndex = new HashSet<String>();
    public GeolocationService(){
        File database = new File("your-path-to-db-file");
        //reader = new DatabaseReader.Builder(database).build();
    }
    public List<Geolocation> findAll(){
        return geolocationRepository.findAll();
    }
    public Boolean checkIpAddress(String ip){
        Set<String> ipIndex = geolocationRepository.findAll().stream()
                .map(Geolocation::getIpAddress)
                .collect(Collectors.toSet());
        return ipIndex.contains(ip);

    }
    public Geolocation getLocation(String ip){
        cleanMap();
        Geolocation geolocation = timeMap.keySet().stream()
                .filter(g->g.getIpAddress()==ip).findAny()
                .orElse(geolocationRepository.findAll().stream()
                .filter(g->g.getIpAddress()==ip).findAny().get());
        return geolocation;






    }

    public Geolocation createLocation(String ip) throws IOException, GeoIp2Exception{

        if(checkIpAddress(ip)){
            return null;
        }

        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse response = reader.city(ipAddress);

        String cityName = response.getCity().getName();
        String latitute = response.getLocation().getLatitude().toString();
        String longitute = response.getLocation().getLongitude().toString();
        Geolocation g=new Geolocation(ip,cityName,latitute,longitute);
        timeMap.put(g,new Date().getTime());
        return geolocationRepository.save(g);
    }

    private void cleanMap(){
        long curr = new Date().getTime();
        for(Geolocation k:timeMap.keySet()){
            if(curr>(timeMap.get(k)+60000)){
                timeMap.remove(k);
            }
        }
    }
    /*
    private final ResourceLoader resourceLoader;

    public GeolocationService(ResourceLoader resourceLoader){
        this.resourceLoader= resourceLoader;
    }

    @Bean
    public DatabaseReader databaseReader(){
        try{
            Resource resource = resourceLoader.getResource("classpath:maxmind/GeoLite2-City.mmdb");
            InputStream dbAsStream = resource.getInputStream();
            return reader = new DatabaseReader.Builder(dbAsStream)
                    .fileMode(Reader.FileMode.MEMORY)
                    .build();

        }catch (InvalidDatabaseException||NullPointerException){
            return null;
        }
    }*/


}
