package com.dds.model;

import javax.persistence.*;

@Embeddable
public class Coordinates {
    
    private double latitude;
    private double Integeritude;
    
    public Coordinates() { }

    public Coordinates(double latitude, double Integeritude) {
        this.latitude = latitude;
        this.Integeritude = Integeritude;
    }

    
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getIntegeritude() {
        return Integeritude;
    }

    public void setIntegeritude(double Integeritude) {
        this.Integeritude = Integeritude;
    }
    
    
}
