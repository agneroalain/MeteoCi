package com.bravaafreeca.meteoci;

/**
 * Created by HP on 30/06/2017.
 */
public class Localisation {

    private int locId;
    private String city;
    private double latitude;
    private double longitude;

    /**
     * No args constructor for use in serialization
     */
    public Localisation() {
    }

    /**
     * @param longitude
     * @param latitude
     * @param locId
     * @param city
     */
    public Localisation(int locId, String city, double latitude, double longitude) {
        super();
        this.locId = locId;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getLocId() {
        return locId;
    }

    public void setLocId(int locId) {
        this.locId = locId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}