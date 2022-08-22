package by.tms.springrest.entity.dao;

import by.tms.springrest.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryUserDao {
    private final AtomicLong atomicLong = new AtomicLong(0);
    private final List<User> users = new ArrayList<>();

    public User save(User user) {
        user.setId(atomicLong.getAndIncrement());
        users.add(user);
        return user;
    }

    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    public Optional<User> getByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public Optional<User> getById(long id) {
        for (User user : users) {
            if (user.getId() == id) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public Optional<User> update(User user) {
       for(int i = 0; i < users.size(); i++) {
           if(user.getId() == users.get(i).getId()) {
               users.set(i, user);
               return Optional.of(users.get(i));
           }
       }
       return Optional.empty();
    }

    public boolean delete(User user) {
        if(users.remove(user)){
            return true;
        }
        return false;
    }

    public Optional<User> deleteById(long id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                User remove = users.remove(i);
                return Optional.of(remove);
            }
        }
        return Optional.empty();
    }
}