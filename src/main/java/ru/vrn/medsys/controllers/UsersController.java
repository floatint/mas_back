package ru.vrn.medsys.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vrn.medsys.entities.User;
import ru.vrn.medsys.services.UsersService;
import ru.vrn.medsys.entities.dto.NewUserDto;
import ru.vrn.medsys.entities.dto.UserDto;

import java.lang.reflect.Type;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api/users")
public class UsersController {
    private final UsersService usersService;
    private final ModelMapper mapper;

    @Autowired
    public UsersController(UsersService service, ModelMapper modelMapper){
        usersService = service;
        mapper = modelMapper;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Iterable<UserDto>> getAllUsers(){
        Type responseCollectionType = new TypeToken<Iterable<UserDto>>(){}.getType();
        return ResponseEntity.ok(mapper.map(usersService.findAll(), responseCollectionType));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        Optional<User> userOp = usersService.findById(id);
        if (!userOp.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(mapper.map(userOp.get(), UserDto.class));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<UserDto> createUser(@RequestBody NewUserDto newUser){
        Stream<User> usersStream = StreamSupport.stream(usersService.findAll().spliterator(), false);
        User user = usersStream.filter(x -> x.getEmail().equals(newUser.getEmail())).findFirst().orElse(null);
        //check if login already exists
        if (user != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        /*
            Тут надо как-то прикрутить роль юзеру
        * */
        return ResponseEntity.ok(mapper.map(user, UserDto.class));
    }

    //TODO: Есть сомнения, что можно сделать по другому
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto newUser){
        Optional<User> userOp = usersService.findById(id);
        if (!userOp.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        newUser.setId(userOp.get().getId());
        User tmpUser = mapper.map(newUser, User.class);
        return ResponseEntity.ok(mapper.map(usersService.save(tmpUser), UserDto.class));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id){
        Optional<User> userOp = usersService.findById(id);
        if (!userOp.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        usersService.delete(userOp.get());
        return ResponseEntity.ok(mapper.map(userOp.get(), UserDto.class));
    }

}
