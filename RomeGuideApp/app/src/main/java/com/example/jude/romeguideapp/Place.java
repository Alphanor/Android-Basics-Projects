package com.example.jude.romeguideapp;

public class Place {

    private String nameOfPlace;
    private String description;
    private String address;
    private String phoneNumber;
    private String webPage;
    private String workingHours;
    private double longitude;
    private double latitude;
    private int imageResourceId;

    //variable created to create two constructor with same input types

    private int temp;

    //constructor for MuseumsFragment

    public Place(String nameOfPlace, String address, String description, String phoneNumber, String webPage, String workingHours, double latitude, double longitude, int imageResourceId) {
        this.nameOfPlace = nameOfPlace;
        this.address = address;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.webPage = webPage;
        this.workingHours = workingHours;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageResourceId = imageResourceId;
    }

    //constructor for FamousPlacesFragment

   public Place(String nameOfPlace, String address, String description, String workingHours, double latitude, double longitude, int imageResourceId) {
        this.nameOfPlace = nameOfPlace;
        this.address = address;
        this.description = description;
        this.workingHours = workingHours;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageResourceId = imageResourceId;
    }

    //Constructor for HotelsFragment and HospitalsFragment

    public Place(String nameOfPlace, String address, String phoneNumber, String webPage, double latitude, double longitude, int imageResourceId, int temp) {
        this.nameOfPlace = nameOfPlace;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.webPage = webPage;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageResourceId = imageResourceId;
        this.temp = temp;
    }


    public String getNameOfPlace() {
        return nameOfPlace;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getWebPage() {
        return webPage;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
