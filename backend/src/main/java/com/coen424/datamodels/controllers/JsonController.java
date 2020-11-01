package com.coen424.datamodels.controllers;

import com.coen424.datamodels.models.Metric;
import com.coen424.datamodels.models.Request;
import com.coen424.datamodels.models.Workload;
import com.coen424.datamodels.services.WorkloadService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api/json")
public class JsonController {

    @Autowired
    private WorkloadService workloadService;

    @GetMapping("/rfw/{rfwid}/benchmark/{benchmark}/metric/{metric}/batch/{unit}/{id}/{size}")
    public ResponseEntity<String> sendSerializedData(@PathVariable(name = "rfwid") int rfwid,
                                                      @PathVariable(name = "benchmark") String benchmark,
                                                      @PathVariable(name = "metric") String metric,
                                                      @PathVariable(name = "unit") int unit,
                                                      @PathVariable(name = "id") int id,
                                                      @PathVariable(name = "size") int size) {

        //This is the request received from the front end
        Request request = new Request(rfwid, benchmark, metric, unit, id, size);
        System.out.println(request.toString());

        //... Obtain workload based on request
        Workload workload = workloadService.getWorkload(request);
        //... Serialize workload into JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().serializeNulls().create();
        String json = gson.toJson(workload);
        System.out.println(json);
        //... Send serialized data to front-end (frontend will deserialize JSON)
        return ResponseEntity.ok(json);
    }
}
