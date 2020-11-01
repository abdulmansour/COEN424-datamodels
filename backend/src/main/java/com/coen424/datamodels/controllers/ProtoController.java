package com.coen424.datamodels.controllers;

import com.coen424.datamodels.models.Request;
import com.coen424.datamodels.models.Workload;
import com.coen424.datamodels.services.Response;
import com.coen424.datamodels.services.WorkloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.OutputStream;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api/proto")
public class ProtoController {

    @Autowired
    private WorkloadService workloadService;

    @GetMapping("/rfw/{rfwid}/benchmark/{benchmark}/metric/{metric}/batch/{unit}/{id}/{size}")
    public ResponseEntity<byte[]> sendSerializedData(@PathVariable(name = "rfwid") int rfwid,
                                                           @PathVariable(name = "benchmark") String benchmark,
                                                           @PathVariable(name = "metric") String metric,
                                                           @PathVariable(name = "unit") int unit,
                                                           @PathVariable(name = "id") int id,
                                                           @PathVariable(name = "size") int size) {

        //This is the request received from the front end
        Request request = new Request(rfwid, benchmark, metric, unit, id, size);
        System.out.println(request.toString());
        Response.Batches.Builder message = Response.Batches.newBuilder();
         message = workloadService.getWorkloadProto(request);
         //message.

        /** TODO:
         //... Serialize data into proto binary based on datamodel
         //... Send serialized data to front-end (frontend will deserialize proto binary)
         */

        return ResponseEntity.ok(message.build().toByteArray());
    }
}
