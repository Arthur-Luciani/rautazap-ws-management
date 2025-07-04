package com.ifsc.rautazap.wsmanagement.infra.adapters.output.redis;

import com.ifsc.rautazap.wsmanagement.application.ports.output.UserRepository;
import com.ifsc.rautazap.wsmanagement.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUserRepositoryAdapter implements UserRepository {

    private static final String USER_PRESENCE_KEY = "online_users";

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisUserRepositoryAdapter(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void saveUser(User user) {
        String userId = user.snapshot().value();
        if (user.isOnline()) {
            stringRedisTemplate.opsForSet().add(USER_PRESENCE_KEY, userId);
        } else {
            stringRedisTemplate.opsForSet().remove(USER_PRESENCE_KEY, userId);
        }
    }

    @Override
    public User findUserById(User.UserId userId) {
        boolean isOnline = Boolean.TRUE.equals(stringRedisTemplate.opsForSet().isMember(USER_PRESENCE_KEY, userId.value()));
        return new User(userId.value(), isOnline);
    }
}
