package net.dezang.restdocopenapi;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
class UserService {
    public List<User> list() {
        return Collections.singletonList(User.builder()
            .id(1L)
            .username("dezang")
            .password("!topSecret!")
            .age(20)
            .enabled(true)
            .build());
    }
}