package com.example.spring_api.service;

import org.springframework.stereotype.Service;
import com.example.spring_api.api.model.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

@Service
public class UserService {

    // Connect to a database realistically....
    // Below is simulating a database
    private List<User> userList;

    public UserService()
    {
        userList = new ArrayList<>();

        User user1 = new User(1, "Dob", 11, "dob@mail.com");
        User user2 = new User(1, "Dob", 11, "dob@mail.com");
        User user3 = new User(1, "Dob", 11, "dob@mail.com");
        User user4 = new User(1, "Dob", 11, "dob@mail.com");
        User user5 = new User(1, "Dob", 11, "dob@mail.com");

        userList.addAll(Arrays.asList(user1, user2, user3, user4, user5));
    }
    //----------------------------------- End of database

    public Optional<User> getUser(Integer id)
    {
        Optional optional = Optional.empty();
        for (User user: userList)
        {
            if (id == user.getID())
            {
                optional = Optional.of(user);
                return optional;
            }
        }

        return optional;
    }
}
