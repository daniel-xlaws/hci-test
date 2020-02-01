package com.test.hci.controller;

import com.test.hci.service.UserService;
import com.test.hci.tuple.UserModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/get-module/{USER_ID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@PathVariable("USER_ID") String userId) {
        UserModuleList moduleList = new UserModuleList();

        try {
            moduleList.setModelModuleList(userService.selectAllUserModules(userId));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(moduleList);
    }
}
