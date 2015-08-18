package com.sourabh.businessService;

import com.sourabh.DTO.LoginDTO;
import com.sourabh.DTO.RegisterDTO;
import com.sourabh.DTO.UserDetailsDTO;

public interface AuthenticationInterface {

    UserDetailsDTO doLogin(LoginDTO loginDTO);

    UserDetailsDTO doRegister(RegisterDTO loginDTO);
}
