package com.coen424.datamodels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.util.ArrayList;

@SpringBootApplication
public class DatamodelsApplication {

	public static void main(String[] args) throws FileNotFoundException {

		ArrayList<BenchMark> dataset = new ArrayList<BenchMark>();
		BenchMarkLoader loader = new BenchMarkLoader("/Users/Joseph D'Onofrio/Desktop/Reader/Data/DVD-testing.csv"); //check path

		dataset = loader.benchMarkFile();

		SpringApplication.run(DatamodelsApplication.class, args);
	}

}
