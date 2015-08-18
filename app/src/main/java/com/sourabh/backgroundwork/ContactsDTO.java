package com.sourabh.backgroundwork;

public class ContactsDTO {
    public String name;
    public String number;
    public String locationAdded;
    public String details;

    public ContactsDTO(String name, String number, String locationAdded, String details) {
        this.name = name;
        this.number = number;
        this.locationAdded = locationAdded;
        this.details = details;

    }

    public String toJsonString() {

        String jsonString = "{\"name\":\"" + name + "\",\"number\":\"" + number + "\",\"locationAdded\":\"" + locationAdded + "\",\"details\":\"\"},";
        return jsonString;

    }
}
