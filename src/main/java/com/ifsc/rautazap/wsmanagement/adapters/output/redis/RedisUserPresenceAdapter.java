package com.ifsc.rautazap.wsmanagement.adapters.output.redis;

import com.ifsc.rautazap.wsmanagement.ports.output.UserPresencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUserPresenceAdapter implements UserPresencePort {

    private static final String USER_PRESENCE_KEY = "online_users";

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisUserPresenceAdapter(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void addUserOnline(String userId) {
        stringRedisTemplate.opsForSet().add(USER_PRESENCE_KEY, userId);
    }

    @Override
    public void removeUserOnline(String userId) {
        stringRedisTemplate.opsForSet().remove(USER_PRESENCE_KEY, userId);
    }

    @Override
    public boolean isUserOnline(String userId) {
        return Boolean.TRUE.equals(stringRedisTemplate.opsForSet().isMember(USER_PRESENCE_KEY, userId));
    }
}
