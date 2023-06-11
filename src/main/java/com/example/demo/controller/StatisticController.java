package com.example.demo.controller;

import com.example.demo.entity.Statistic;
import com.example.demo.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;


    @GetMapping("/statistics")
    public List<Statistic> getList(){
        return statisticService.getStatisticList();
    }

}
