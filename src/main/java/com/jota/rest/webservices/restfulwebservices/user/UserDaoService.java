package com.jota.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService{
    private static List<User> users  = new ArrayList<>();

    static{
        users.add(new User(1, "jp", LocalDate.now().minusYears(30)));
        users.add(new User(2, "jp4", LocalDate.now().minusYears(35)));
        users.add(new User(3, "jp3", LocalDate.now().minusYears(40)));
    }

    public List<User> findAll(){
        return users;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User save(User user){
        Integer cuenta= ( users.size() +1);
        user.setId(  cuenta  );
        users.add(user);
        return user;
    }

    public void deleteById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);


    }
}
