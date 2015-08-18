package com.sourabh.entity;

public class News {

    String newsId;
    String title;
    String reporter;
    String postingDate;
    String endDate;
    String images;
    String detail;
    String categoryId;
    String commentsId;
    String type;
    String status;
    Category category;
    String locality;
    String advertiseImage;
    String packageName;

    String versionCode;

    String updated;

    String size;

    String installs;

    String androidRequired;

    String rating;

    String permissions;

    String developer;

    String whatsNew;
    String city;
    String state;
    String country;

    public News() {
    }

    public News(String newsId, String title, String reporter, String postingDate, String endDate, String images, String detail, String categoryId, String commentsId, String type, String status, Category category, String locality, String advertiseImage, String packageName, String versionCode, String updated, String size, String installs, String androidRequired, String rating, String permissions, String developer, String whatsNew, String city, String state, String country) {
        this.newsId = newsId;
        this.title = title;
        this.reporter = reporter;
        this.postingDate = postingDate;
        this.endDate = endDate;
        this.images = images;
        this.detail = detail;
        this.categoryId = categoryId;
        this.commentsId = commentsId;
        this.type = type;
        this.status = status;
        this.category = category;
        this.locality = locality;
        this.advertiseImage = advertiseImage;
        this.packageName = packageName;
        this.versionCode = versionCode;
        this.updated = updated;
        this.size = size;
        this.installs = installs;
        this.androidRequired = androidRequired;
        this.rating = rating;
        this.permissions = permissions;
        this.developer = developer;
        this.whatsNew = whatsNew;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getInstalls() {
        return installs;
    }

    public void setInstalls(String installs) {
        this.installs = installs;
    }

    public String getAndroidRequired() {
        return androidRequired;
    }

    public void setAndroidRequired(String androidRequired) {
        this.androidRequired = androidRequired;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getWhatsNew() {
        return whatsNew;
    }

    public void setWhatsNew(String whatsNew) {
        this.whatsNew = whatsNew;
    }

    public String getAdvertiseImage() {
        return advertiseImage;
    }

    public void setAdvertiseImage(String advertiseImage) {
        this.advertiseImage = advertiseImage;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(String postingDate) {
        this.postingDate = postingDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(String commentsId) {
        this.commentsId = commentsId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toStringBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getCategory().getCategory() + " ");
        stringBuilder.append(getCategory().getDetail() + " ");
        stringBuilder.append(getDetail() + " ");
        stringBuilder.append(getCity() + " ");
        stringBuilder.append(getReporter() + " ");
        stringBuilder.append(getCountry() + " ");
        stringBuilder.append(getLocality() + " ");
        stringBuilder.append(getState() + " ");
        stringBuilder.append(getTitle() + " ");
        stringBuilder.append(getType() + " ");
        return stringBuilder.toString().toLowerCase();
    }

    public String toShare() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getTitle() + " " + "\n\n");
        stringBuilder.append(getReporter() + " \t" + getPostingDate() + "\n");
        stringBuilder.append(getDetail() + " " + "\n");


        return stringBuilder.toString();
    }

}
