package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.controller.exception.UserNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserService service;

    @Autowired
    private CartService cartService;

    @PostMapping(value = "/createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) throws UserNotFoundException {
        User user = mapper.mapToUser(userDto);
        user.setCart(cartService.saveCart(new Cart()));
        User savedUser = service.saveUser(user);
        generateRandomKey(savedUser.getId());
        service.saveUser(savedUser);
    }

    @GetMapping(value = "/getUsers")
    public List<UserDto> getUsers() {
        List<User> users = service.getUsers();
        return mapper.mapToUserDtoList(users);
    }

    @GetMapping(value = "/getUser")
    public UserDto getUser(@RequestParam Long userId) throws UserNotFoundException {
        User user = service.getUser(userId).orElseThrow(() -> new UserNotFoundException("User with " + userId + " id, not exists"));
        return mapper.mapToUserDto(user);
    }

    @DeleteMapping(value = "/deleteUser")
    public void deleteUser(@RequestParam Long userId) {
        service.deleteUser(userId);
    }

    @PutMapping(value = "/updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        User user = mapper.mapToUser(userDto);
        User savedUser = service.saveUser(user);
        return mapper.mapToUserDto(savedUser);
    }

    @PatchMapping(value = "/blockUser")
    public void blockUser(@RequestParam Long userId) throws UserNotFoundException {
        service.blockUser(userId);
    }

    @PatchMapping(value = "/unblockUser")
    public void unblockUser(@RequestParam Long userId) throws UserNotFoundException{
        service.unblockUser(userId);
    }

    @PatchMapping(value = "/generateRandomKey")
    public UserDto generateRandomKey(@RequestParam Long userId) throws UserNotFoundException{
        return mapper.mapToUserDto(service.generateRandomKey(userId));
    }
}
