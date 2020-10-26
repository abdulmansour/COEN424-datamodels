package com.coen424.datamodels.controllers;

import com.coen424.datamodels.models.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
public class Controller {

    @GetMapping("/json/rfw/{rfwid}/benchmark/{benchmark}/metric/{metric}/batch/{unit}/{id}/{size}")
    public ResponseEntity<Request> getDataJson(@PathVariable(name = "rfwid") String rfwid,
                                        @PathVariable(name = "benchmark") String benchmark,
                                        @PathVariable(name = "metric") String metric,
                                        @PathVariable(name = "unit") int unit,
                                        @PathVariable(name = "id") String id,
                                        @PathVariable(name = "size") int size) {

        Request request = new Request(rfwid, benchmark, metric, unit, id, size);

        //... Fetch CSV Data
        //... JSON Serialization
        //... Send serialized data

        return ResponseEntity.ok(request);
    }

    @GetMapping("proto/rfw/{rfwid}/benchmark/{benchmark}/metric/{metric}/batch/{unit}/{id}/{size}")
    public ResponseEntity<Request> getDataProto(@PathVariable(name = "rfwid") String rfwid,
                                           @PathVariable(name = "benchmark") String benchmark,
                                           @PathVariable(name = "metric") String metric,
                                           @PathVariable(name = "unit") int unit,
                                           @PathVariable(name = "id") String id,
                                           @PathVariable(name = "size") int size) {

        Request request = new Request(rfwid, benchmark, metric, unit, id, size);

        //... Fetch CSV Data
        //... Proto Serialization
        //... Send serialized data

        return ResponseEntity.ok(request);
    }

}
