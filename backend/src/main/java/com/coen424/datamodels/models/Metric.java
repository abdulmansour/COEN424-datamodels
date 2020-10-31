package com.coen424.datamodels.models;

import com.google.gson.annotations.Expose;

public class Metric {

    private double cpuUtilization;
    private double networkInAverage;
    private double networkOutAverage;
    private double memoryUlitlizationAverage;
    @Expose
    private double chosenMetric;

    public Metric(int cpu, double netIn, double netOut, double mem){
        this.cpuUtilization = cpu;
        this.networkInAverage = netIn;
        this.networkOutAverage = netOut;
        this.memoryUlitlizationAverage = mem;
    }

    public double getChosenMetric() {
        return chosenMetric;
    }

    public void setChosenMetric(double chosenMetric) {
        this.chosenMetric = chosenMetric;
    }

    public double getCpuUtilization() {
        return cpuUtilization;
    }

    public void setCpuUtilization(double cpuUtilization) {
        this.cpuUtilization = cpuUtilization;
    }

    public double getNetworkInAverage() {
        return networkInAverage;
    }

    public void setNetworkInAverage(double networkInAverage) {
        this.networkInAverage = networkInAverage;
    }

    public double getNetworkOutAverage() {
        return networkOutAverage;
    }

    public void setNetworkOutAverage(double networkOutAverage) {
        this.networkOutAverage = networkOutAverage;
    }

    public double getMemoryUlitlizationAverage() {
        return memoryUlitlizationAverage;
    }

    public void setMemoryUlitlizationAverage(double memoryUlitlizationAverage) {
        this.memoryUlitlizationAverage = memoryUlitlizationAverage;
    }

    @Override
    public String toString() {
        return "Metric{" +
                "chosenMetric=" + chosenMetric +
                '}';
    }
}
