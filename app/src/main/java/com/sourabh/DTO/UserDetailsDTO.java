package com.sourabh.DTO;

import com.sourabh.entity.User;

public class UserDetailsDTO {

    User user;
    private Boolean isAuthentic = false;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getIsAuthentic() {
        return isAuthentic;
    }

    public void setIsAuthentic(Boolean isAuthentic) {
        this.isAuthentic = isAuthentic;
    }


}

