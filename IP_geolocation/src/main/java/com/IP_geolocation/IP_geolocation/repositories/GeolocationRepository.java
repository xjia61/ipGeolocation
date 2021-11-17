package com.IP_geolocation.IP_geolocation.repositories;

import com.IP_geolocation.IP_geolocation.model.Geolocation;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.Resource;

@Resource
public interface GeolocationRepository extends JpaRepository<Geolocation,Long> {
}
