package net.dezang.restdocopenapi;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
class User {
    private Long id;
    private String username;
    private String password;
    private Integer age;
    private boolean enabled;
}