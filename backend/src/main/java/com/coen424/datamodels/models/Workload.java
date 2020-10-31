package com.coen424.datamodels.models;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Workload {
    @Expose
    private String rfwid;
    @Expose
    private long lastBatchId;
    @Expose
    private List<List<Metric>> samplesRequested;

    public Workload(String rfwid, long lastBatchId, List<List<Metric>> samplesRequested) {
        this.rfwid = rfwid;
        this.lastBatchId = lastBatchId;
        this.samplesRequested = samplesRequested;
    }

    public String getRfwid() {
        return rfwid;
    }

    public void setRfwid(String rfwid) {
        this.rfwid = rfwid;
    }

    public long getLastBatchId() {
        return lastBatchId;
    }

    public void setLastBatchId(long lastBatchId) {
        this.lastBatchId = lastBatchId;
    }

    public List<List<Metric>> getSamplesRequested() {
        return samplesRequested;
    }

    public void setSamplesRequested(List<List<Metric>> samplesRequested) {
        this.samplesRequested = samplesRequested;
    }

    @Override
    public String toString() {
        return "Workload{" +
                "rfwid='" + rfwid + '\'' +
                ", lastBatchId=" + lastBatchId +
                "}";
    }
}
