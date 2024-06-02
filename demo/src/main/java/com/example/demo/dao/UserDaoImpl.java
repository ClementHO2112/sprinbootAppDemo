package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Repository
public class UserDaoImpl implements UserDao{
    @Override
    public User findByUserName(String userName) {
        final String uri = "http://mockserver:1080/employee?username=" + userName;

        RestTemplate restTemplate = new RestTemplate();

        try{
            return restTemplate.getForObject(uri, User.class);
        } catch (HttpClientErrorException httpErr){
            if(httpErr.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new UsernameNotFoundException("Invalid username or password.");
            }
        }

        return null;
    }
}
