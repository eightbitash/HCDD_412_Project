package org.example.HCDD_412_Project.Service;

import org.example.HCDD_412_Project.Model.User;
import org.example.HCDD_412_Project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service

public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        // run findByUsername and send username as a parameter
        // assign the resulting value to a User object named user
        User user = userRepository.findByUsername(username);

        // if the value of user is == null, there was no user found
        if (user == null)
        {
            throw new UsernameNotFoundException("User not found");
        }
        // if the value of user !== null, the user *was* found in the database
        else
        {
           return new org.springframework.security.core.userdetails.User(
                   user.getUsername(),
                   user.getPassword(),
                   Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
           );
        }
    }
}
