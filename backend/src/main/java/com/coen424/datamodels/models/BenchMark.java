package com.coen424.datamodels.models;

public class BenchMark {

    private int cpuUtilization;
    private double networkInAverage;
    private double networkOutAverage;
    private double memoryUlitlizationAverage;

    public BenchMark(int cpu, double netIn, double netOut, double mem){
        this.cpuUtilization = cpu;
        this.networkInAverage = netIn;
        this.networkOutAverage = netOut;
        this.memoryUlitlizationAverage = mem;
    }

    public int getCpuUtilization() {
        return cpuUtilization;
    }

    public void setCpuUtilization(int cpuUtilization) {
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
}
