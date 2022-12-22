package mate.academy.spring.controller;

import javax.persistence.EntityNotFoundException;
import mate.academy.spring.mapper.DtoResponseMapper;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.response.UserResponseDto;
import mate.academy.spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final DtoResponseMapper<UserResponseDto, User> userResponseDtoUserDtoResponseMapper;

    public UserController(UserService userService,
                          DtoResponseMapper<UserResponseDto, User>
                                  userResponseDtoUserDtoResponseMapper) {
        this.userService = userService;
        this.userResponseDtoUserDtoResponseMapper = userResponseDtoUserDtoResponseMapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto getUserByEmail(@RequestParam String email) {
        return userResponseDtoUserDtoResponseMapper.toDto(userService.findByEmail(email)
                .orElseThrow(() ->
                        new EntityNotFoundException("Can't find user by email " + email)));
    }
}