package com.sourabh.DTO;

public class CouponResponseDTO {

    String couponCode;
    String expiryDate;

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String toJsonString() {

        String string = "[{\"couponCode\":\"" + couponCode + "\",\"expiryDate\":\"" + expiryDate + "\"}]";


        return string;

    }
}
