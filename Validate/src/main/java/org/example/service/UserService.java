package org.example.service;

import org.example.dto.request.UserInfoDetails;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private EmailSenderService emailSenderService;
    public User save(User user) {
        return repository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetail = repository.findByEmail(username);
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public Boolean checkExistEmail(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username).isPresent();
    }

    public User loadUserByEmail(String username){
        Optional<User> userDetail = repository.findByEmail(username);
        return userDetail.get();
    }

    public String addUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
        return "User Added Successfully";
    }
    public boolean checkFb(String name, String idProvider){
        Optional<User> user = repository.findByNameAndIdProviderAndLoginSource(name, idProvider);
        return user.isPresent();
    }

    public User loadUserByNameAndIdProvider(String name, String idProvider){
        Optional<User> user = repository.findByNameAndIdProviderAndLoginSource(name, idProvider);
        return  user.get();
    }
}
