package kr.ac.jejunu.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {
    @GetMapping(value = "{id}")
    @ResponseBody //이게 없으면 rest.jsp를 찾게됨
    public User get(@PathVariable("id") Integer id) {
        return User.builder().id(id).name("hulk").password("1234").build();
    }

    @PostMapping
    public User add(@RequestBody User user) {
        return user;
    }
}
