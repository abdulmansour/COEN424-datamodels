package com.coen424.datamodels.models;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Workload {
    @Expose
    private int rfwid;
    @Expose
    private long lastBatchId;
    @Expose
    private List<List<Metric>> samplesRequested;

    public Workload(int rfwid, long lastBatchId, List<List<Metric>> samplesRequested) {
        this.rfwid = rfwid;
        this.lastBatchId = lastBatchId;
        this.samplesRequested = samplesRequested;
    }

    public int getRfwid() {
        return rfwid;
    }

    public void setRfwid(int rfwid) {
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
