package com.coen424.datamodels.models;

import com.coen424.datamodels.services.Response;

import java.util.ArrayList;

public class BatchLoader {
    private Request request;
    private int lineCount;
    private String metric;
    private ArrayList<Metric> dataset = new ArrayList<Metric>();

    public BatchLoader(Request request, ArrayList<Metric> dataset) {
        this.request = request;
        this.lineCount = request.getBatchUnit() * request.getBatchId();
        this.metric = request.getWorkloadMetric();
        this.dataset = dataset;
    }

    public Response.Batches.Builder generator(){

        Response.Batches.Builder output = Response.Batches.newBuilder();

        output.setRfwid(request.getRfwid());
        output.setLastID(request.getBatchId() + request.getBatchSize() - 1);

        //System.out.println(output.getRfwid() + "  " + output.getLastID());

        for(int i = 0; i < request.getBatchSize(); i++){
            output.addBatches(batch());
        }

        return output;
    }

    private Response.Batch batch(){
        Response.Batch.Builder batch = Response.Batch.newBuilder();

        for(int j = 0; j < request.getBatchUnit(); j++){
            batch.addMetrics(metric());
        }

        return batch.build();
    }

    public Response.Metric metric(){
        Response.Metric.Builder metricOut = Response.Metric.newBuilder();

        metricOut.setId(lineCount);

        switch (metric){
            case "CPU":
                metricOut.setMetric(dataset.get(lineCount++).getCpuUtilization());
                break;
            case "NetworkIn":
                metricOut.setMetric(dataset.get(lineCount++).getNetworkInAverage());
                break;
            case "NetworkOut":
                metricOut.setMetric(dataset.get(lineCount++).getNetworkOutAverage());
                break;
            case  "Memory":
                metricOut.setMetric(dataset.get(lineCount++).getMemoryUlitlizationAverage());
                break;
        }

        //System.out.println(metricOut);

        return metricOut.build();
    }
}
