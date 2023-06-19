package com.example.demo.controller;

import com.example.demo.dto.Currency;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExternalApiController {

    private final RestTemplate restTemplate;

    @ApiOperation(value = "get currency api", response = Long.class)
    @GetMapping(value = "/currency")
    public List<Object> getCurrency() {
        List<Object> currencyList = new ArrayList<>();

        Object usd = restTemplate.getForObject("https://cbu.uz/uz/arkhiv-kursov-valyut/json/USD/840", Object.class);
        currencyList.add(usd);
        Object euro = restTemplate.getForObject("https://cbu.uz/uz/arkhiv-kursov-valyut/json/EUR/978", Object.class);
        currencyList.add(euro);
        Object rubl =  restTemplate.getForObject("https://cbu.uz/uz/arkhiv-kursov-valyut/json/RUB/643", Object.class);
        currencyList.add(rubl);

        return currencyList;
    }


}
