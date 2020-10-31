package com.coen424.datamodels.services;

import com.coen424.datamodels.models.Metric;
import com.coen424.datamodels.models.Request;
import com.coen424.datamodels.models.Workload;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class WorkloadService {

    private String line = "";
    private String spliter = ",";
    private HashMap<String, ArrayList<Metric>> benchmarks = new HashMap<String, ArrayList<Metric>>();

    public WorkloadService() {
        List<String> list = Arrays.asList("DVD-testing", "DVD-training", "NDBench-testing", "NDBench-training");

        for (String file: list) {
            try {
                ArrayList<Metric> bm = new ArrayList<>();
                File resource = new ClassPathResource("benchmarks/" + file + ".csv").getFile();

                BufferedReader br = new BufferedReader(new FileReader(resource));
                br.readLine(); // skip first line

                while((line = br.readLine()) != null){
                    String[] data = line.split(spliter);
                    bm.add(new Metric(Integer.parseInt(data[0]),Double.parseDouble(data[1]),Double.parseDouble(data[2]), Double.parseDouble(data[3])));
                    //System.out.println(data[0] + "|" + data[1] + "|" + data[2] + "|" + data[3]);
                }
                benchmarks.put(file, bm);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Workload getWorkload(Request request)  {
        ArrayList<Metric> benchmark = benchmarks.get(request.getBenchmarkType());
        Workload workload = new Workload(request.getRfwid(), 0, new ArrayList<List<Metric>>());

        ArrayList<List<Metric>> batches = new ArrayList<>();

        for (int i = 0; i < benchmark.size(); i += request.getBatchUnit()) {
            if (i+request.getBatchUnit()-1 < benchmark.size()) {
                batches.add(benchmark.subList(i, i + request.getBatchUnit()));
            }
            else {
                //batches.add(benchmark.subList(i, i + ( i + request.getBatchUnit() - benchmark.size()-1)));
                //or ignore last batch if reaches limit...
            }
        }

        int start = request.getBatchId() - 1 <= batches.size()? request.getBatchId() - 1:batches.size()-1;
        int end = start + request.getBatchSize();
        end = Math.min(end, batches.size());
        List<List<Metric>> requiredBatches = batches.subList(start, end);

        for (List<Metric> batch:requiredBatches) {
            for (Metric metric:batch) {
                if(request.getWorkloadMetric().equals("CPU")) {
                    metric.setChosenMetric(metric.getCpuUtilization());
                }
                else if(request.getWorkloadMetric().equals("NetworkIn")) {
                    metric.setChosenMetric(metric.getNetworkInAverage());
                }
                else if(request.getWorkloadMetric().equals("NetworkOut")) {
                    metric.setChosenMetric(metric.getNetworkOutAverage());
                }
                else if(request.getWorkloadMetric().equals("Memory")) {
                    metric.setChosenMetric(metric.getMemoryUlitlizationAverage());
                }
            }
        }

        workload.setSamplesRequested(requiredBatches);
        workload.setLastBatchId(end);

        //print out the workload
        System.out.println(workload.toString());
        int i = request.getBatchId() < workload.getLastBatchId()? request.getBatchId():(int)workload.getLastBatchId();
        for (List<Metric> batch:workload.getSamplesRequested()) {
            System.out.println(i++);
            for (Metric m:batch) {
                System.out.println(m.toString());
            }
        }
        System.out.println();

        return workload;
    }
}
