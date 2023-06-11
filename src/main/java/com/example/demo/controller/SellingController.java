package com.example.demo.controller;

import com.example.demo.entity.Selling;
import com.example.demo.service.SellingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SellingController {

    private final SellingService sellingService;

    @GetMapping("/selling")
    public List<Selling> getList() {
        return sellingService.getList();
    }

    @GetMapping("/sum-value")
    public double getSumValue() {
        return sellingService.getSumValue();
    }

    @GetMapping("/numbers")
    public int getNumbers() {
        return sellingService.getTotalNumber();
    }

    @PostMapping("/selling/add")
    public String save(@RequestBody Selling selling) {
        return sellingService.save(selling);
    }

    @GetMapping("/report/{beginDate}/{endDate}")
    public void downloadAnimalHusbandryClusterTotal(HttpServletResponse response, @PathVariable("beginDate") String beginDate, @PathVariable("endDate") String endDate) throws Exception {
        sellingService.excelCollector(response, Date.valueOf(beginDate), Date.valueOf(endDate));
    }

}
