package ru.vrn.medsys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vrn.medsys.entities.SecurityUserDetails;
import ru.vrn.medsys.entities.User;
import ru.vrn.medsys.repositories.UserRepository;

import java.util.Optional;

@Service
public class UsersService implements UserDetailsService {
    //@Autowired
    private final UserRepository userRepository;

    @Autowired
    public UsersService(UserRepository repository){
        userRepository = repository;
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    public User save(User u){
        return userRepository.save(u);
    }

    public Iterable<User> saveAll(Iterable<User> u){
        return userRepository.saveAll(u);
    }

    public void delete(User u){
        userRepository.delete(u);
    }

    public Optional<User> findByEmail(String login){
        return userRepository.findByEmail(login);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Iterable<User> users = userRepository.findAll();
        Optional<User> user = userRepository.findByEmail(s);
        user.orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", s)));

        return user.map(SecurityUserDetails::new).get();
    }
}
