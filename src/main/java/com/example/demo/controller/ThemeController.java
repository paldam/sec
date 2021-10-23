package com.example.demo.controller;

import com.example.demo.model.Theme;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.ThemeService;
import com.example.demo.service.UserServiceImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class ThemeController {

    private final ThemeService themeService;
    private final UserRepository userRepository;
    private final UserServiceImpl userServiceImpl;

    public ThemeController(ThemeService themeService, UserRepository userRepository, UserServiceImpl userServiceImpl) {
        this.themeService = themeService;
        this.userRepository = userRepository;
       this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/theme/{host}")
    public ResponseEntity<Theme> getThemeInfo(@PathVariable String host) throws JSONException {

        List<Theme> themes= themeService.fetchWordPressThemeInfo(host);
        this.themeService.saveThemes(themes);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/rates")
    public ResponseEntity<String> getRates() throws JSONException {

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/tables/A", String.class);

        JSONArray jsonObject = new JSONArray(response);

        JSONObject jsonObject2 = jsonObject.getJSONObject(0);

        return ResponseEntity.ok(jsonObject2.getString("effectiveDate"));
    }

    @PostMapping("/saveuser")
    public ResponseEntity<User> saveUser(@RequestBody User user) {


        userServiceImpl.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/theme")
    public ResponseEntity<String> getThemeInfo2() {


        return ResponseEntity.ok("ffffff");
    }

    @RequestMapping(path = "/joke", method = RequestMethod.GET)
    public String getJokes(Model model) {
//        List<UserInfo> users = userService.getUsers();
//        model.addAttribute("users", users);
//        model.addAttribute("userInfo", new UserInfo());
        return "jokes";
    }

    @GetMapping("/home")
    public String getHomePage(Model model) {
        return "home";
    }

    @GetMapping("/welcome")
    public String getWelcomePage() {
        return "hello";
    }

}
