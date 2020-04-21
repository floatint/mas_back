package ru.vrn.medsys.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper(){
        //new user mapper
        /*PropertyMap<NewUserDto, User> newUserMap = new PropertyMap<NewUserDto, User>() {
            @Override
            protected void configure() {
                map().setPassword(new BCryptPasswordEncoder().encode(source.getPassword()));
                //map().setPassword(new BCryptPasswordEncoder().encode(source.getPassword()));
            }
        };*/
        ModelMapper mapper = new ModelMapper();
        //mapper.addMappings(newUserMap);
        return mapper;
    }
}
