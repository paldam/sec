package com.example.demo.service;

import com.example.demo.model.Root;
import com.example.demo.model.Theme;
import com.example.demo.repositories.ThemeRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ThemeService {

    ThemeRepository themeRepository;

    public ThemeService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public List<Theme> fetchWordPressThemeInfo(String host) throws JSONException {

        final String API_KEY = "wambbspk7cp5giz5cbmlt6z2dd56cdv4lvj37grlbkaw1stznyxxkj9uh33fkkcw2zil7y";
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://www.themedetect.com/API/Theme?url=" + host + "&key=" + API_KEY;

        Root response = restTemplate.getForObject(url, Root.class);


        String response2 = restTemplate.getForObject(url, String.class);
       getOneValueFromJson(response2);

        return response.results;


    }


    public void  getOneValueFromJson(String  response ) throws JSONException {
         System.out.println(response);


        JSONObject jsonObject = new JSONObject(response);
        jsonObject.getString("result");
        //JSONArray value = jsonObject.getJSONArray("results");
        //System.out.println(value.getString(0));
        System.out.println(jsonObject.getString("result"));
    }

    public List<Theme> saveThemes(List<Theme> theme) {
        return themeRepository.saveAll(theme);
    }
}