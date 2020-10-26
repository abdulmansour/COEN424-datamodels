package com.coen424.datamodels.controllers;

import com.coen424.datamodels.models.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api/proto")
public class ProtoController {

    @GetMapping("/rfw/{rfwid}/benchmark/{benchmark}/metric/{metric}/batch/{unit}/{id}/{size}")
    public ResponseEntity<Request> sendSerializedData(@PathVariable(name = "rfwid") String rfwid,
                                                      @PathVariable(name = "benchmark") String benchmark,
                                                      @PathVariable(name = "metric") String metric,
                                                      @PathVariable(name = "unit") int unit,
                                                      @PathVariable(name = "id") String id,
                                                      @PathVariable(name = "size") int size) {

        //This is the request received from the front end
        Request request = new Request(rfwid, benchmark, metric, unit, id, size);
        System.out.println(request.toString());

        /** TODO:
         //... Fetch CSV Data
         //... Serialize data into proto binary based on datamodel
         //... Send serialized data to front-end (frontend will deserialize proto binary)
         */

        return ResponseEntity.ok(request);
    }
}