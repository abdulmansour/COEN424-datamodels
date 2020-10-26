package com.coen424.datamodels.models;

public class Request {
    private String rfwid;
    private String benchmarkType;
    private String workloadMetric;
    private int batchUnit;
    private String batchId;
    private int batchSize;

    public Request(String rfwid, String benchmarkType, String workloadMetric, int batchUnit, String batchId, int batchSize) {
        this.rfwid = rfwid;
        this.benchmarkType = benchmarkType;
        this.workloadMetric = workloadMetric;
        this.batchUnit = batchUnit;
        this.batchId = batchId;
        this.batchSize = batchSize;
    }

    public String getRfwid() {
        return rfwid;
    }

    public void setRfwid(String rfwid) {
        this.rfwid = rfwid;
    }

    public String getBenchmarkType() {
        return benchmarkType;
    }

    public void setBenchmarkType(String benchmarkType) {
        this.benchmarkType = benchmarkType;
    }

    public String getWorkloadMetric() {
        return workloadMetric;
    }

    public void setWorkloadMetric(String workloadMetric) {
        this.workloadMetric = workloadMetric;
    }

    public int getBatchUnit() {
        return batchUnit;
    }

    public void setBatchUnit(int batchUnit) {
        this.batchUnit = batchUnit;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }
}
