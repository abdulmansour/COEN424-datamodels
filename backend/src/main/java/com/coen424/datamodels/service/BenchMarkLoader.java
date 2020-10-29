package com.coen424.datamodels.service;

import com.coen424.datamodels.models.BenchMark;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BenchMarkLoader {

    private String name;
    private String line = "";
    private String spliter = ",";
    private ArrayList<BenchMark> benchMarks = new ArrayList<BenchMark>();

    public BenchMarkLoader(String name){
        this.name = name;
    }

    public BenchMarkLoader() {
    }

    public ArrayList<BenchMark> benchMarkFile() throws FileNotFoundException {

        try (BufferedReader br = new BufferedReader(new FileReader(name))){
            br.readLine(); // skip first line

            while((line = br.readLine()) != null){

                String[] data = line.split(spliter);

                benchMarks.add(new BenchMark(Integer.parseInt(data[0]),Double.parseDouble(data[1]),Double.parseDouble(data[2]), Double.parseDouble(data[3])));
                System.out.println(data[0] + "|" + data[1] + "|" + data[2] + "|" + data[3]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return benchMarks;
    }
}
