package com.coen424.datamodels.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/json")
public class JSONController {

    @GetMapping("/benchmark/{benchmark}/metric/{metric}/batch/{unit}/{id}/{size}")
    public ResponseEntity<String> getData(@PathVariable(name = "benchmark") String benchmark,
                                               @PathVariable(name = "metric") String metric,
                                               @PathVariable(name = "unit") String unit,
                                               @PathVariable(name = "id") String id,
                                               @PathVariable(name = "size") String size) {

        // call service -> service will fetch .csv data and serialize it based on data model -> return to client via http

        return ResponseEntity.ok("");
    }
}
