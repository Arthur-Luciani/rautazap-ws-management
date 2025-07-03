package com.ifsc.rautazap.wsmanagement.infra.adapters.output.redis;

import com.ifsc.rautazap.wsmanagement.domain.user.UserId;
import com.ifsc.rautazap.wsmanagement.application.ports.output.UserPresencePort;
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
    public void addUserOnline(UserId userId) {
        stringRedisTemplate.opsForSet().add(USER_PRESENCE_KEY, userId.value());
    }

    @Override
    public void removeUserOnline(UserId userId) {
        stringRedisTemplate.opsForSet().remove(USER_PRESENCE_KEY, userId.value());
    }

    @Override
    public boolean isUserOnline(UserId userId) {
        return Boolean.TRUE.equals(stringRedisTemplate.opsForSet().isMember(USER_PRESENCE_KEY, userId.value()));
    }
}
