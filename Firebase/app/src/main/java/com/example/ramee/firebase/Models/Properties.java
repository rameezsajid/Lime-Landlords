package com.example.ramee.firebase.Models;

/**
 * Created by ramee on 28/03/2018.
 */

public class Properties {
    String propertyID;
    String propertyLocation;
    String propertyType;
    String propertyRental;
    String propertyLength;
    String propertyAddress;
    String propertyPostcode;
    String managementType;
    String managementName;

    public Properties() {
    }

    public Properties(String propertyID, String propertyLocation, String propertyType, String propertyRental, String propertyLength, String propertyAddress, String propertyPostcode, String managementType, String managementName) {
        this.propertyID = propertyID;
        this.propertyLocation = propertyLocation;
        this.propertyType = propertyType;
        this.propertyRental = propertyRental;
        this.propertyLength = propertyLength;
        this.propertyAddress = propertyAddress;
        this.propertyPostcode = propertyPostcode;
        this.managementType = managementType;
        this.managementName = managementName;
    }

    public String getPropertyID() {
        return propertyID;
    }

    public String getPropertyLocation() {
        return propertyLocation;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public String getPropertyRental() {
        return propertyRental;
    }

    public String getPropertyLength() {
        return propertyLength;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public String getPropertyPostcode() {
        return propertyPostcode;
    }

    public String getManagementType() {
        return managementType;
    }

    public String getManagementName() {
        return managementName;
    }
}
