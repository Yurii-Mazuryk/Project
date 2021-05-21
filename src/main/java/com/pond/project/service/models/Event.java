package com.pond.project.service.models;



public class Event {
    private int id;
    private String date;
    private String name;
    private String address;
    private int countOfParticipant;
    private int countOfReports;

    public int getCountOfReports() {
        return countOfReports;
    }

    public void setCountOfReports(int countOfReports) {
        this.countOfReports = countOfReports;
    }



    public int getCountOfParticipant() {
        return countOfParticipant;
    }

    public void setCountOfParticipant(int countOfParticipant) {
        this.countOfParticipant = countOfParticipant;
    }


    public Event(int id, String date, String name, String address) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.address = address;
    }

    public Event(String date, String name, String address) {
        this.date = date;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Event(){
    }
}
