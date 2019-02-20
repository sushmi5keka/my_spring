package com.example.hellomvc.Controler;

import com.example.hellomvc.Entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    static List<User> list;

    static {
        list = new ArrayList<>();

        list.add(new User(1L, "sushmi", "01500000000"));
        list.add(new User(2L, "Mola", "01600000000"));
        list.add(new User(3L, "Roju", "01700000000"));

    }

    @GetMapping(value = "/")
    public String home() {
        return "Hello vaa";
    }

    @GetMapping(value = "/test")
    public String test() {
        return "Hello moo";
    }


    @GetMapping(value = "/users")
    public List<User> getListt() {
        return this.list;
    }

    @GetMapping(value = "/user/{id}")
    public User getUserById(@PathVariable("id") Long id) {

        User user = null;
        for (User u : this.list) {
            if (id == u.getId()) {
                user = new User();
                user = new User(u.getId(), u.getUsername(), u.getMobile());
                break;
            }
        }
        return user;
    }


    @GetMapping(value = "/user/{id}/{mobile}")
    public User getUser(@PathVariable("id") Long id, @PathVariable("mobile") String mb) {

        User user = null;
        for (User u : this.list) {
            if (id == u.getId() && mb.equalsIgnoreCase(u.getMobile())) {
                user = new User();
                user = new User(u.getId(), u.getUsername(), u.getMobile());
                break;
            }
        }
        return user;
    }

    @GetMapping(value = "/addUser")
    public List<User> addUserToList() {
        list.add(new User(4L, "Raju", "01800000000"));

        return list;
    }

    long id = list.size();

    @GetMapping(value = "/addUser/{username}/{mobile}")
    public List<User> addUserdinamic(@PathVariable("username") String user, @PathVariable("mobile") String mb) {
        id++;
        list.add(new User(id, user, mb));

        return list;
    }

    @GetMapping(value = "/delete/{id}")
    public List<User> removeUserById(@PathVariable("id") Long id) {
        list.remove(getUserById(id));

        return list;
    }

}
