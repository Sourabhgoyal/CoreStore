package com.sourabh.events.entity;

public class Event {
    String images;
    String going;
    String eventId;
    String eventName;
    String eventLocation;
    String eventAddress;
    String eventStartDateTime;
    String eventEndDateTime;
    String category;
    String detail;
    String peopleJoining;
    String organizerDetail;
    String attractionPoint;
    String status;
    String city;
    String state;
    String country;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getGoing() {
        return going;
    }

    public void setGoing(String going) {
        this.going = going;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public String getEventStartDateTime() {
        return eventStartDateTime;
    }

    public void setEventStartDateTime(String eventStartDateTime) {
        this.eventStartDateTime = eventStartDateTime;
    }

    public String getEventEndDateTime() {
        return eventEndDateTime;
    }

    public void setEventEndDateTime(String eventEndDateTime) {
        this.eventEndDateTime = eventEndDateTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPeopleJoining() {
        return peopleJoining;
    }

    public void setPeopleJoining(String peopleJoining) {
        this.peopleJoining = peopleJoining;
    }

    public String getOrganizerDetail() {
        return organizerDetail;
    }

    public void setOrganizerDetail(String organizerDetail) {
        this.organizerDetail = organizerDetail;
    }

    public String getAttractionPoint() {
        return attractionPoint;
    }

    public void setAttractionPoint(String attractionPoint) {
        this.attractionPoint = attractionPoint;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String toShare() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getEventName() + "\n");
        stringBuilder.append(getAttractionPoint() + "\n");
        stringBuilder.append(getDetail() + "\n");
        stringBuilder.append(getEventStartDateTime() + "\t");
        stringBuilder.append(getEventEndDateTime() + "\n");
        stringBuilder.append(getEventLocation() + "\n");
        stringBuilder.append(getEventAddress() + "\n");
        stringBuilder.append(getCity() + "\n");
        stringBuilder.append(getOrganizerDetail() + "\n");

        return stringBuilder.toString();


    }

}
