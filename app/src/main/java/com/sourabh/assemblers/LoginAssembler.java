package com.sourabh.assemblers;

import com.sourabh.DTO.LoginDTO;
import com.sourabh.DTO.RegisterDTO;
import com.sourabh.DTO.UserDetailsDTO;
import com.sourabh.entity.User;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginAssembler {

    private User user;
    private LoginDTO loginDTO;
    private RegisterDTO registerDTO;

    public LoginAssembler() {
        user = User.getInstance();
    }

    public UserDetailsDTO loginResponseToUser(String json) {

        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            user.setUsername(jsonObject.getString("username"));
            user.setPassword(jsonObject.getString("password"));
            user.setAddress(jsonObject.getString("address"));
            user.setAge(jsonObject.getString("age"));
            user.setCity(jsonObject.getString("city"));
            user.setCredit(jsonObject.getString("credit"));
            user.setDateOfRegistration(jsonObject.getString("dateOfRegistration"));
            user.setDetails(jsonObject.getString("details"));
            user.setEmailAddress(jsonObject.getString("emailAddress"));
            user.setLocality(jsonObject.getString("locality"));
            user.setMobileNo(jsonObject.getString("mobileNo"));
            user.setPincode(jsonObject.getString("pincode"));
            user.setRefferalId(jsonObject.getString("referralId"));
            user.setState(jsonObject.getString("state"));
            user.setUserId(jsonObject.getString("userId"));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        userDetailsDTO.setUser(user);
        if (user.getUserId() != null && !user.getUserId().equals("null") && !user.getUserId().equals(""))
            userDetailsDTO.setIsAuthentic(true);

        return userDetailsDTO;


    }

    public UserDetailsDTO responseToRegisterDTO(String json) {

        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        try {
            JSONObject jsonObject = new JSONObject(json);
            user.setUsername(jsonObject.getString("username"));
            user.setPassword(jsonObject.getString("password"));
            user.setEmailAddress(jsonObject.getString("emailAddress"));
            user.setMobileNo(jsonObject.getString("mobileNo"));
            user.setRefferalId(jsonObject.getString("refferalId"));
            userDetailsDTO.setIsAuthentic(true);
            userDetailsDTO.setUser(user);
            return userDetailsDTO;
        } catch (JSONException e) {

            e.printStackTrace();
            return null;
        }

	
	/*user.setUsername(response.getProperty("username").toString());
    user.setPassword(response.getProperty("password").toString());
	user.setUserId(response.getProperty("username").toString());
	user.setMobileNo(response.getProperty("password").toString());
	user.setLocality(response.getProperty("username").toString());
	user.setCity(response.getProperty("password").toString());
	user.setState(response.getProperty("password").toString());
	user.setPincode(response.getProperty("password").toString());
	user.setAge(response.getProperty("password").toString());
	user.setDetails(response.getProperty("password").toString());
	user.setCredit(response.getProperty("password").toString());*/
    }
}
