package com.max.shop.security;

import com.max.shop.exception.UserNotFoundException;
import com.max.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final CacheManager cacheManager;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userRepository.findByUsername(name).orElseThrow(UserNotFoundException::new);
    }
}
