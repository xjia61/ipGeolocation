package com.IP_geolocation.IP_geolocation.model;

import javax.persistence.*;

@Entity
@Table(name = "geolocations")
public class Geolocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "query")
    private String query;

    @Column(name = "status")
    private String status;

    @Column(name = "country")
    private String country;

    @Column(name = "countryCode")
    private String countryCode;

    @Column(name = "region")
    private String region;

    @Column(name = "regionName")
    private String regionName;

    @Column(name = "city")
    private String city;

    @Column(name = "zip")
    private String zip;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lon" )
    private Double lon;

    @Column(name = "timezone")
    private String timezone;

    @Column(name = "isp")
    private String isp;

    @Column(name = "org")
    private String org;

    @Column(name = "as")
    private String as;


    public Geolocation(){}

    public Geolocation(String query, String status, String country, String countryCode, String region, String regionName, String city, String zip, Double lat, Double lon, String timezone, String isp, String org, String as) {
        this.query = query;
        this.status = status;
        this.country = country;
        this.countryCode = countryCode;
        this.region = region;
        this.regionName = regionName;
        this.city = city;
        this.zip = zip;
        this.lat = lat;
        this.lon = lon;
        this.timezone = timezone;
        this.isp = isp;
        this.org = org;
        this.as = as;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public void setAs(String as) {
        this.as = as;
    }

    public String getQuery() {
        return query;
    }

    public String getStatus() {
        return status;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getRegion() {
        return region;
    }

    public String getRegionName() {
        return regionName;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getIsp() {
        return isp;
    }

    public String getOrg() {
        return org;
    }

    public String getAs() {
        return as;
    }
}
