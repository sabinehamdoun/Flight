package com.halaezzeddine.fly;

import java.util.Date;

public class Form {

    private String id;
    private String from;
    private String to;
    private String departure;
    private String arrival;
    private String travelClass;

/////constructors


    public Form() {

    }

    public Form(String id, String from, String to, String departure, String arrival, String travelClass) {
        this.id=id;
        this.from=from;
        this.to=to;
        this.arrival=arrival;
        this.departure=departure;
        this.travelClass=travelClass;
    }

    public Form(String from, String to, String departure, String arrival, String travelClass) {
        this.from=from;
        this.to=to;
        this.arrival=arrival;
        this.departure=departure;
        this.travelClass=travelClass;
    }






/////Setters and getters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(String travelClass) {
        this.travelClass = travelClass;
    }
}
