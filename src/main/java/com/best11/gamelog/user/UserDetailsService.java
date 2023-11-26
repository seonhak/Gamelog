package com.best11.gamelog.user;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.best11.gamelog.user.entity.User;
import com.best11.gamelog.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsImpl getUserDetails(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found" + username));
        return new UserDetailsImpl(user);
    }

}