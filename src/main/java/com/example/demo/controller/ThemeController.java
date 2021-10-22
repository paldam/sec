package com.example.demo.controller;

import com.example.demo.model.Theme;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.ThemeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ThemeController {

    private final ThemeService themeService;
    private final UserRepository userRepository;

    public ThemeController(ThemeService themeService, UserRepository userRepository ) {
        this.themeService = themeService;
        this.userRepository = userRepository;
    }

    @GetMapping("/theme/{host}")
    public ResponseEntity<Theme> getThemeInfo(@PathVariable String host) {

        List<Theme> themes= themeService.fetchWordPressThemeInfo(host);
        this.themeService.saveThemes(themes);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/saveuser")
    public ResponseEntity<User> saveUser(@RequestBody User user) {

        userRepository.save(user);
        return ResponseEntity.ok(null);
    }
}
