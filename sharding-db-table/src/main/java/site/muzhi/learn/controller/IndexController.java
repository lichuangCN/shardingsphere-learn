package site.muzhi.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.muzhi.common.entity.User;
import site.muzhi.common.result.Result;
import site.muzhi.learn.service.UserService;

/**
 * @author lichuang
 * @date 2021/03/05
 * @description
 */
@RestController
public class IndexController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/save")
    public Result saveUser() {
        for (int i = 0; i < 10; i++) {
            User user = new User("Tom_" + i);
            userService.save(user);
        }
        return Result.ok();
    }

    @GetMapping("/user/list")
    public Result listUser() {
        return Result.ok(userService.list());
    }

    @GetMapping("/user/{id}")
    public Result userById(@PathVariable("id") Long id) {
        return Result.ok(userService.findById(id));
    }

    @GetMapping("/user/name")
    public Result listUserByName(@RequestParam("name") String name) {
        return Result.ok(userService.listByName(name));
    }
}
