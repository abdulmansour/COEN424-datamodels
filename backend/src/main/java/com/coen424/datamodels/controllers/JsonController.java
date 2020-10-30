package com.coen424.datamodels.controllers;

import com.coen424.datamodels.models.Metric;
import com.coen424.datamodels.models.Request;
import com.coen424.datamodels.models.Workload;
import com.coen424.datamodels.services.WorkloadService;
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
    public ResponseEntity<Workload> sendSerializedData(@PathVariable(name = "rfwid") String rfwid,
                                                      @PathVariable(name = "benchmark") String benchmark,
                                                      @PathVariable(name = "metric") String metric,
                                                      @PathVariable(name = "unit") int unit,
                                                      @PathVariable(name = "id") int id,
                                                      @PathVariable(name = "size") int size) {

        //This is the request received from the front end
        Request request = new Request(rfwid, benchmark, metric, unit, id, size);
        System.out.println(request.toString());

        Workload workload = workloadService.getWorkload(request);

        System.out.println(workload.toString());
        int i = request.getBatchId() < workload.getLastBatchId()? request.getBatchId():(int)workload.getLastBatchId();
        for (List<Metric> batch:workload.getSamplesRequested()) {
            System.out.println(i++);
            for (Metric m:batch) {
                System.out.println(m.toString());
            }
        }
        System.out.println();
        /** TODO:
        //... Serialize data into JSON based on datamodel
        //... Send serialized data to front-end (frontend will deserialize JSON)
         */

        return ResponseEntity.ok(workload);
    }
}
