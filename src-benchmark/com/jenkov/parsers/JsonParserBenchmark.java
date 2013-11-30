package com.jenkov.parsers;

import com.jenkov.parsers.core.DataCharBuffer;
import com.jenkov.parsers.core.IndexBuffer;
import com.jenkov.parsers.json.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 */
public class JsonParserBenchmark {

    public static void main(String[] args) throws IOException {

        System.out.println("Benchmark v. 1.0.1");
        String fileName = "data/small.json.txt";
        if(args.length > 0) {
            fileName = args[0];
        }
        System.out.println("parsing: " + fileName);

        DataCharBuffer dataCharBuffer = FileUtil.readFile(fileName);
        IndexBuffer jsonTokens   = new IndexBuffer(8192, true);
        IndexBuffer jsonElements = new IndexBuffer(8192, true);

        JsonParser jsonParser    = new JsonParser(jsonTokens, jsonElements);

        int iterations = 1* 1000 * 1000; //
        long startTime = System.currentTimeMillis();
        for(int i=0; i<iterations; i++) {
            parse(dataCharBuffer, jsonParser, jsonElements);
        }
        long endTime = System.currentTimeMillis();

        long finalTime = endTime - startTime;

        System.out.println("final time: " + finalTime);

    }

    private static void parse(DataCharBuffer dataCharBuffer, JsonParser jsonParser, IndexBuffer jsonElements) {
        jsonParser.parse(dataCharBuffer);
    }

}
