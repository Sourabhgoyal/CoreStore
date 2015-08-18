package com.sourabh.DTO;

public class RegisterDTO {

    private String username;
    private String password;
    private String emailAddress;
    private String mobileNo;
    private String refferalId;
    private String dateOfRegistration;
    private String address;
    private String locality;
    private String city;
    private String state;
    private String userid;
    private String pincode;
    private String age;
    private String details;
    private String credit;

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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getRefferalId() {
        return refferalId;
    }

    public void setRefferalId(String refferalId) {
        this.refferalId = refferalId;
    }

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getRefferalCode() {
        return refferalId;
    }

    public void setRefferalCode(String refferalcode) {
        this.refferalId = refferalcode;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
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

    public String toJsonString() {

        String string = "[{\"username\":\"" + username + "\",\"password\":\"" + password + "\",\"referralId\":\"" + refferalId + "\",\"userid\":\"\",\"dateOfRegistration\":\"" + dateOfRegistration + "\",\"emailAddress\":\"" + emailAddress + "\",\"mobileNo\":\"" + mobileNo + "\",\"address\":\"" + address + "\",\"locality\":\"" + locality + "\",\"city\":\"" + city + "\",\"state\":\"" + state + "\",\"pincode\":\"" + pincode + "\",\"age\":\"" + age + "\",\"details\":\"" + details + "\",\"credit\":\"" + credit + "\"}]";
        return string;

    }
}
