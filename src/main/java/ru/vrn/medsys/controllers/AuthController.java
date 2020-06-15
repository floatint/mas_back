package ru.vrn.medsys.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import ru.vrn.medsys.entities.Role;
import ru.vrn.medsys.entities.SecurityUserDetails;
import ru.vrn.medsys.entities.User;
import ru.vrn.medsys.services.UsersService;
import ru.vrn.medsys.entities.dto.NewUserDto;
import ru.vrn.medsys.entities.dto.UserDto;
import ru.vrn.medsys.repositories.RoleRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/auth")
@Api(value = "auth", description = "Контроллер авторизации")
public class AuthController {

    private final UsersService usersService;
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;


    @Autowired
    public AuthController(UsersService usersService, RoleRepository roleRepository, ModelMapper mapper){
        this.usersService = usersService;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "Вход в систему", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Вход выполнен успешно"),
            @ApiResponse(code = 401, message = "Не удалось выполнить вход"),
            @ApiResponse(code = 500, message = "Ошибка сервера")
    }
    )
    public ResponseEntity<UserDto> signIn(Principal p){
        SecurityUserDetails userDetails = (SecurityUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(mapper.map(userDetails, UserDto.class));
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "Регистрация в системе", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Регистрация выполнена успешно"),
            @ApiResponse(code = 400, message = "Пользователь с таким email сущетсвует"),
            @ApiResponse(code = 500, message = "Ошибка сервера")

    }
    )
    public ResponseEntity<UserDto> signUp(@RequestBody NewUserDto newUser){

        //check if login already exists
        Optional<User> tmp = usersService.findByEmail(newUser.getEmail());
        if (tmp.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        User user = mapper.map(newUser, User.class);
        user.setRoles(new ArrayList<>());
        Optional<Role> role = roleRepository.findByName("ROLE_CLIENT");
        if (!role.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        user.getRoles().add(role.get());
        //encode pass
        user.setPassword(SecurityUserDetails.passwordEncoder.encode(user.getPassword()));
        usersService.save(user);


        return ResponseEntity.ok(mapper.map(user, UserDto.class));
    }

}
