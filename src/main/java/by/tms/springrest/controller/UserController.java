package by.tms.springrest.controller;

import by.tms.springrest.entity.dao.InMemoryUserDao;
import by.tms.springrest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    final private InMemoryUserDao inMemoryUserDao = new InMemoryUserDao();

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> all = inMemoryUserDao.findAll();
        if (all.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(all);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody User user) {
        User save = inMemoryUserDao.save(user);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping("/username")
    public ResponseEntity<User> getByUsername(String username) {
        Optional<User> byUsername = inMemoryUserDao.getByUsername(username);
        if (byUsername.isPresent()) {
            return ResponseEntity.ok(byUsername.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/id")
    public ResponseEntity<User> getById(long id) {
        Optional<User> byId = inMemoryUserDao.getById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<User> update(@Valid @RequestBody User user) {
        Optional<User> updated = inMemoryUserDao.update(user);
        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<User> delete(@Valid @RequestBody User user) {
        if (inMemoryUserDao.delete(user)) {
            return ResponseEntity.ok(user);

        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/id")
    public ResponseEntity<User> deleteById(long id) {
        Optional<User> user = inMemoryUserDao.deleteById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.notFound().build();
    }
}
