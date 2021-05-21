package com.pond.project.service.models;

public class Report {
    private int id;
    private String title;
    private String text;
    private int speakerId;
    private String eventName;
    private boolean isReportConfirmed = false;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }


    public void setReportConfirmed(boolean reportConfirmed) {
        isReportConfirmed = reportConfirmed;
    }

    public boolean isReportConfirmed() {
        return isReportConfirmed;
    }


    public Report(int id, String title, String text, String eventName) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.eventName = eventName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(int speakerId) {
        this.speakerId = speakerId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Report() {
    }

}
