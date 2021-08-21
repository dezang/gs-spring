package net.dezang.restdocopenapi;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto.Response>> list() {
        List<UserDto.Response> responses = userService.list().stream()
            .map(UserDto.Response::of)
            .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static class UserDto {
        @Data
        @AllArgsConstructor
        static class Response {
            private String username;
            private Integer age;

            public static Response of(User domain) {
                return new Response(domain.getUsername(), domain.getAge());
            }
        }
    }
}
