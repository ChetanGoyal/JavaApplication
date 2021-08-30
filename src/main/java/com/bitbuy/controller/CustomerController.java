package com.bitbuy.controller;

import com.bitbuy.Utility;
import com.bitbuy.entity.User;
import com.bitbuy.exception.AuthenticationFailedException;
import com.bitbuy.exception.UpdateUserException;
import com.bitbuy.exception.UserIdNotFoundException;
import com.bitbuy.exception.UserRestrictedException;
import com.bitbuy.model.UpdateUserRequest;
import com.bitbuy.model.UserCreateRequest;
import com.bitbuy.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
public class CustomerController {

    private Utility utility;
    private UserService userService;

    public CustomerController(Utility utility,UserService userService) {
        this.utility = utility;
        this.userService = userService;
    }

    @RequestMapping(method= RequestMethod.POST, path="/api/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(
            @RequestBody(required = true)final UserCreateRequest userCreateRequest)
            throws UserRestrictedException {

        User user = new User();
        user.setUserName(userCreateRequest.getUserName());
        user.setPassword(userCreateRequest.getPassword());
        user.setName(userCreateRequest.getName());
        user.setEmail(userCreateRequest.getEmail());
        user.setPhone(userCreateRequest.getPhone());

        utility.isValidRequestForRegister(user);
        User createUser = userService.saveUser(user);

        return new ResponseEntity<>(200, HttpStatus.OK);

    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/api/login",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestHeader("authorization") final String authorization)
        throws AuthenticationFailedException {
        byte[] decode;
        String userName;
        String password;

        decode = Base64.getDecoder().decode(authorization.split("Basic ")[1]);
        String decodedText = new String(decode);
        String[] decodedArray = decodedText.split(":");
        userName = decodedArray[0];
        password = decodedArray[1];

        User user = userService.authenticate(userName,password);

        return new ResponseEntity<>(200,HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/api/users/{uuid}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserDetails(@RequestHeader("authorization") final String authorization,
                                                 @PathVariable("uuid") final String uuid)
    throws AuthenticationFailedException, UserIdNotFoundException {
        String accessToken = authorization.split("Bearer ")[1];

        User user = userService.getUser(accessToken);

        return new ResponseEntity<>(user.toString(),HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/api/users/{uuid}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(
            @RequestHeader("authorization") final String authorization,
            @RequestBody(required = true) final UpdateUserRequest updateUserRequest
            ) throws UpdateUserException,AuthenticationFailedException{
        if (updateUserRequest.getName() == null
                || updateUserRequest.getName().isEmpty()) {
            throw new UpdateUserException("UCR-002", " Name field should not be empty");
        }

        String accessToken = authorization.split("Bearer ")[1];
        User user = userService.getUser(accessToken);

        user.setName(updateUserRequest.getName());
        user.setPhone(updateUserRequest.getPhone());
        user.setEmail(updateUserRequest.getEmail());

        User userUpdate = userService.updateUser(user);

        return new ResponseEntity<>(200,HttpStatus.OK);
    }
}
