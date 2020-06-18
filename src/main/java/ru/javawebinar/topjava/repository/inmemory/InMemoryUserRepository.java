package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.AbstractNamedEntity;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);
    private Map<Integer, User> userRepository = new ConcurrentHashMap<>();
//    private AtomicInteger userCounter = new AtomicInteger(0);


    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return userRepository.remove(id) != null;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        return userRepository.put(SecurityUtil.authUserId(), user);
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return userRepository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return userRepository.values().stream()
                .sorted(Comparator.comparing(User::getName).thenComparing(User::getEmail))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return userRepository.values().stream()
                .filter(a -> a.getEmail().equals(email))
                .findAny()
                .get();
    }
}
