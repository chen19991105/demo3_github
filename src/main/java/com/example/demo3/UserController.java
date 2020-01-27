package com.example.demo3;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    //同步锁
    private List<User2> listUser= Collections.synchronizedList(new ArrayList<User2>());
    //获取全部用户信息
    @GetMapping("/")
    public List<User2> getuserList(){
        return listUser;
    }
    //新增用户
    @PostMapping("/")
    public String createUser(User2 user){
        listUser.add(user);
        return "success";
    }
    //获取指定id用户
    @GetMapping("/{id}")
    public User2 getuserone(@PathVariable("id") Long id){
        for (User2 user : listUser) {
            if(user.getId()==id){
                return user;
            }
        }
        return null;
    }
    //更新指定用户
    @PutMapping("/{id}")
    public String putuser(@PathVariable("id") Long id, User2 user){
        for (User2 user1 : listUser) {
            if(user1.getId()==id){
                user1.setName(user.getName());
                user1.setAge(user.getAge());
            }
        }
        return "success";
    }
    //删除指定用户
    @DeleteMapping("/{id}")
    public String deleteuserone(@PathVariable("id") Long id){

        listUser.remove(getuserone(id));

        return "success";
    }
}
