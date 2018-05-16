package com.saurin.demojwt.service;

import com.saurin.demojwt.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AccountUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username)
                .map(account -> new User(account.getUsername(), account.getPassword(), account.isActive(),
                        account.isActive(), account.isActive(), account.isActive(),
                        AuthorityUtils.createAuthorityList("ROLE_ADMIN")
                ))
                .orElseThrow(()-> new UsernameNotFoundException("User not found!"));
    }

    public List findAllUsers(){
        return accountRepository.findAll();
    }

    private List getAuthority(){
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
