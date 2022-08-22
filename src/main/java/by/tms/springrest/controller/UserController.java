package by.tms.springrest.controller;

import by.tms.springrest.dao.InMemoryUserDao;
import by.tms.springrest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    final private InMemoryUserDao inMemoryUserDao = new InMemoryUserDao();

    @GetMapping
    public List<User> findAll() {
        return inMemoryUserDao.findAll();
    }

    @PostMapping
    public User save(@RequestBody User user) {
        return inMemoryUserDao.save(user);
    }

    @GetMapping("/username")
    public User getByUsername(String username) {
        Optional<User> byUsername = inMemoryUserDao.getByUsername(username);
        if (byUsername.isPresent()) {
            return byUsername.get();
        } else {
            return null;
        }
    }

    @GetMapping("/id")
    public User getById(long id) {
        Optional<User> byId = inMemoryUserDao.getById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            return null;
        }
    }

    @PutMapping
    public User update(@RequestBody User user) {
        Optional<User> updated = inMemoryUserDao.update(user);
        if (updated.isPresent()) {
            return updated.get();
        }
        return null;
    }

    @DeleteMapping
    public void delete(@RequestBody User user) {
        inMemoryUserDao.delete(user);
    }

    @DeleteMapping("/id")
    public User deleteById(long id) {
        Optional<User> user = inMemoryUserDao.deleteById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }
}
