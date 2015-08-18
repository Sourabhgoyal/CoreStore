package com.sourabh.entity;

public class User {

    static User user;
    String username;
    String password;
    String refferalId;
    String userId;
    String dateOfRegistration;
    String emailAddress;
    String mobileNo;
    String address;
    String locality;
    String city;
    String state;
    String pincode;
    String age;
    String details;
    String credit;

    private User() {

    }

    public static User getInstance() {
        if (user == null) {
            user = new User();
            return user;
        } else {
            return user;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRefferalId() {
        return refferalId;
    }

    public void setRefferalId(String refferalId) {
        this.refferalId = refferalId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
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

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String toJsonString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        stringBuilder.append("\"username\"");
        stringBuilder.append("\"password\"");
        stringBuilder.append("}");

        return stringBuilder.toString();

    }

    public String userIdToJsonString(String userId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[{");
        stringBuilder.append("\"userId\":");
        stringBuilder.append("\"" + userId + "\"}]");
        return stringBuilder.toString();
    }

    public void reset() {
        user = new User();
    }
}
