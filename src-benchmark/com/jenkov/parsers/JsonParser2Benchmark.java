package com.jenkov.parsers;

import com.jenkov.parsers.core.DataCharBuffer;
import com.jenkov.parsers.core.IndexBuffer;
import com.jenkov.parsers.json.JsonParser2;

import java.io.IOException;

/**
 */
public class JsonParser2Benchmark {

    public static void main(String[] args) throws IOException {

        System.out.println("Benchmark v. 1.0.1");
        String fileName = "data/small.json.txt";
        if(args.length > 0) {
            fileName = args[0];
        }
        System.out.println("parsing: " + fileName);

        DataCharBuffer dataCharBuffer = FileUtil.readFile(fileName);
        IndexBuffer jsonElements = new IndexBuffer(8192, true);

        JsonParser2 jsonParser    = new JsonParser2();

        int iterations = 10 * 1000 * 1000; //10.000.000 iterations to warm up JIT and minimize one-off overheads etc.
        if(args.length > 1){
            iterations = Integer.parseInt(args[1]);
        }
        System.out.println("iterations: " + iterations);

        long startTime = System.currentTimeMillis();
        for(int i=0; i<iterations; i++) {
            parse(dataCharBuffer, jsonParser, jsonElements);
        }
        long endTime = System.currentTimeMillis();

        long finalTime = endTime - startTime;

        System.out.println("final time: " + finalTime);

    }

    private static void parse(DataCharBuffer dataCharBuffer, JsonParser2 jsonParser, IndexBuffer jsonElements) {
        jsonParser.parse(dataCharBuffer, jsonElements);
    }

}
