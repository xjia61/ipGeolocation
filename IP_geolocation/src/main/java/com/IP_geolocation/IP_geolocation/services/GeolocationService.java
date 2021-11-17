package com.IP_geolocation.IP_geolocation.services;

import com.IP_geolocation.IP_geolocation.model.Geolocation;
import com.IP_geolocation.IP_geolocation.repositories.GeolocationRepository;
import com.maxmind.db.InvalidDatabaseException;
import com.maxmind.db.Reader;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.sun.xml.internal.rngom.digested.DAnnotation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class GeolocationService {
    private static DatabaseReader reader = null;
    private GeolocationRepository geolocationRepository;
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

    public Geolocation getLocation(String ip) throws IOException, GeoIp2Exception{
        if(checkIpAddress(ip)){
            return null;
        }

        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse response = reader.city(ipAddress);

        String cityName = response.getCity().getName();
        String latitute = response.getLocation().getLatitude().toString();
        String longitute = response.getLocation().getLongitude().toString();
        Geolocation g=new Geolocation(ip,cityName,latitute,longitute);
        return geolocationRepository.save(g);
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
