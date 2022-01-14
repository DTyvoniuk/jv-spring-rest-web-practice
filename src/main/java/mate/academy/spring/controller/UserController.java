package mate.academy.spring.controller;

import java.util.NoSuchElementException;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.response.UserResponseDto;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.dto.mapping.DtoResponseMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private DtoResponseMapper<UserResponseDto, User> responseMapper;

    public UserController(UserService userService,
                          DtoResponseMapper<UserResponseDto, User> responseMapper) {
        this.userService = userService;
        this.responseMapper = responseMapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto getByEmail(@RequestParam("email") String email) {
        return responseMapper.toDto(userService.findByEmail(email).orElseThrow(
                () -> new NoSuchElementException("Can't find user with email " + email)));
    }
}