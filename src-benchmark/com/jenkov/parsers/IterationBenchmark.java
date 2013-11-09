package com.jenkov.parsers;

import com.jenkov.parsers.core.DataCharBuffer;

import java.io.IOException;

/**
 */
public class IterationBenchmark {

    public static void main(String[] args) throws IOException {
        String fileName = "data/small.json.txt";
        if(args.length > 0) {
            fileName = args[0];
        }
        System.out.println("iterating: " + fileName);

        DataCharBuffer dataCharBuffer = FileUtil.readFile(fileName);

        int iterations = 10000000;
        long startTime = System.currentTimeMillis();
        for(int i=0; i<iterations; i++) {
            parse(dataCharBuffer);
        }
        long endTime = System.currentTimeMillis();

        long finalTime = endTime - startTime;

        System.out.println("final time: " + finalTime);

    }

    private static void parse(DataCharBuffer dataCharBuffer) {
        for(int j=0; j<dataCharBuffer.length; j++) {
            char theChar = dataCharBuffer.data[j];
        }
    }
}
