package com.vladislav.todo.services;

import com.vladislav.todo.entities.User;
import com.vladislav.todo.repositories.UserRepository;
import com.vladislav.todo.utils.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found user with username: " + username));
    }

    @Override
    public User getAuthenticatedUser() {
        return (User) authenticationFacade.getAuthentication().getPrincipal();
    }
}
