package com.coen424.datamodels.models;

public class Request {
    private int rfwid;
    private String benchmarkType;
    private String workloadMetric;
    private int batchUnit;
    private int batchId;
    private int batchSize;

    public Request(int rfwid, String benchmarkType, String workloadMetric, int batchUnit, int batchId, int batchSize) {
        this.rfwid = rfwid;
        this.benchmarkType = benchmarkType;
        this.workloadMetric = workloadMetric;
        this.batchUnit = batchUnit;
        this.batchId = batchId;
        this.batchSize = batchSize;
    }

    public int getRfwid() {
        return rfwid;
    }

    public void setRfwid(int rfwid) {
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

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    @Override
    public String toString() {
        return "Request{" +
                "rfwid='" + rfwid + '\'' +
                ", benchmarkType='" + benchmarkType + '\'' +
                ", workloadMetric='" + workloadMetric + '\'' +
                ", batchUnit=" + batchUnit +
                ", batchId=" + batchId +
                ", batchSize=" + batchSize +
                '}';
    }
}
