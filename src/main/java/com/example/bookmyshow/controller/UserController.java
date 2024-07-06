package com.example.bookmyshow.controller;

import com.example.bookmyshow.Services.UserService;
import com.example.bookmyshow.dto.ResponseStatus;
import com.example.bookmyshow.dto.SignUpRequestDTO;
import com.example.bookmyshow.dto.SignUpResponseDTO;
import com.example.bookmyshow.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    public SignUpResponseDTO signUp(SignUpRequestDTO requestDTO){
        SignUpResponseDTO response = new SignUpResponseDTO();

        try{
            User user = userService.signUp(
                    requestDTO.getEmail(),
                    requestDTO.getPassword()
            );
            response.setUserId(user.getId());
            response.setResponseStatus(ResponseStatus.SUCCESS);

        } catch (Exception ex){
            response.setResponseStatus(ResponseStatus.FAILURE);
            response.setMessage(ex.getMessage());
        }
        return response;
    }
}
