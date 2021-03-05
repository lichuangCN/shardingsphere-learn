package site.muzhi.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.muzhi.common.entity.City;
import site.muzhi.common.entity.User;
import site.muzhi.common.result.Result;
import site.muzhi.learn.service.CityService;
import site.muzhi.learn.service.UserService;

/**
 * @author lichuang
 * @date 2021/03/05
 * @description
 */
@RestController
public class IndexController {
    @Autowired
    private CityService cityService;
    @Autowired
    private UserService userService;

    @PostMapping("/city/save")
    public Result saveCity() {
        for (int i = 0; i < 2; i++) {
            City city = new City("无锡");
            cityService.save(city);
        }
        return Result.ok();
    }

    @GetMapping("/city/list")
    public Result listCity() {
        return Result.ok(cityService.list());
    }

    @GetMapping("/city/{id}")
    public Result cityById(@PathVariable("id") Long id) {
        return Result.ok(cityService.findById(id));
    }

    @GetMapping("/city/name")
    public Result listCityByName(@RequestParam("name") String name) {
        return Result.ok(cityService.listByName(name));
    }

    @PostMapping("/user/save")
    public Result saveUser() {
        for (int i = 0; i < 2; i++) {
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
