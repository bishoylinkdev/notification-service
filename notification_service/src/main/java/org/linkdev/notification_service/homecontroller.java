package org.linkdev.notification_service;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class homecontroller {
    @GetMapping
    public String homepage()
    { return "home page";}
    @PutMapping("/user")
    public void userlogin(@RequestBody User user)
    {
        System.out.println(user.username);
        System.out.println(user.userid);
    }


}
