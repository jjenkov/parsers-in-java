package com.jenkov.parsers;

import com.google.gson.Gson;
import com.jenkov.parsers.core.DataCharBuffer;

import java.io.CharArrayReader;
import java.io.IOException;

/**
 */
public class GsonObjectTreeBenchmark {

    public static void main(String[] args) throws IOException {
        String fileName = "data/small.json.txt";
        if(args.length > 0) {
            fileName = args[0];
        }
        System.out.println("parsing: " + fileName);

        DataCharBuffer dataCharBuffer = FileUtil.readFile(fileName);
        Gson gson = new Gson();

        int iterations = 10 * 1000 * 1000; //10.000.000 iterations to warm up JIT and minimize one-off overheads etc.
        if(args.length > 1){
            iterations = Integer.parseInt(args[1]);
        }
        System.out.println("iterations: " + iterations);

        long startTime = System.currentTimeMillis();
        for(int i=0; i<iterations; i++) {
            parse(dataCharBuffer, gson);
        }
        long endTime = System.currentTimeMillis();

        long finalTime = endTime - startTime;

        System.out.println("final time: " + finalTime);
    }

    private static void parse(DataCharBuffer dataCharBuffer, Gson gson) {
        JsonObject jsonObject = gson.fromJson(new CharArrayReader(dataCharBuffer.data, 0, dataCharBuffer.length), JsonObject.class);
    }


}
