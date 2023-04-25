package com.tamim.propertyapp;

import java.util.ArrayList;
import java.util.List;

public class Property {
    private String location;
    private Integer rent;
    private Integer bedroomCount;
    private Integer carParkCount;
    private Integer bathroomCount;
    private String imageUrl;

    public Property(String location, Integer rent, Integer bedroomCount, Integer carParkCount, Integer bathroomCount, String imageUrl) {
        this.location = location;
        this.rent = rent;
        this.bedroomCount = bedroomCount;
        this.carParkCount = carParkCount;
        this.bathroomCount = bathroomCount;
        this.imageUrl = imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public Integer getRent() {
        return rent;
    }
    public Integer getBedroomCount() {
        return bedroomCount;
    }
    public Integer getCarParkCount() {
        return carParkCount;
    }
    public Integer getBathroomCount() {
        return bathroomCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRent(Integer rent) {
        this.rent = rent;
    }
    public void setBedroomCount(Integer bedroomCount) {
        this.bedroomCount = bedroomCount;
    }
    public void setCarParkCount(Integer carParkCount) {
        this.carParkCount = carParkCount;
    }
    public void setBathroomCount(Integer bathroomCount) {
        this.bathroomCount = bathroomCount;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static List<Property> loadProperties() {
        List<Property> propertyList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String location = "Flinders Street " + (i+1);
            String imageURL = "https://dummyimage.com/800x600/ffbb86fc&text=Property+Image+" + (i+1);
            Property property = new Property(location, (i+1)*100,3,2,2,imageURL);
            propertyList.add(property);
        }
        return propertyList;
    }
}
