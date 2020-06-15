package ru.vrn.medsys.configurations;

import org.modelmapper.*;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.vrn.medsys.entities.Role;
import ru.vrn.medsys.entities.SecurityUserDetails;
import ru.vrn.medsys.entities.User;
import ru.vrn.medsys.entities.dto.UserDto;

import javax.print.attribute.standard.Destination;
import java.util.stream.Collectors;

@Configuration
public class AppConfig {
    //Type converters
    private Converter<User, UserDto> fromUserToUserDto = new AbstractConverter<User, UserDto>() {
        @Override
        protected UserDto convert(User user) {
            UserDto dto = new UserDto();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            dto.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
            return dto;
        }
    };

    private Converter<SecurityUserDetails, UserDto> fromDetailsToUserDto = new AbstractConverter<SecurityUserDetails, UserDto>() {
        @Override
        protected UserDto convert(SecurityUserDetails securityUserDetails) {
            UserDto dto = new UserDto();
            User u = securityUserDetails.getUser();
            dto.setId(u.getId());
            dto.setName(u.getName());
            dto.setEmail(u.getEmail());
            dto.setRoles(u.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
            return dto;
        }
    };

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.addConverter(fromUserToUserDto);
        mapper.addConverter(fromDetailsToUserDto);
        return mapper;
    }
}
