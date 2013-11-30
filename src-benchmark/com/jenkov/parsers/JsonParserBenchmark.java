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
        String fileName = "data/small.json.txt";
        if(args.length > 0) {
            fileName = args[0];
        }
        System.out.println("parsing: " + fileName);

        DataCharBuffer dataCharBuffer = FileUtil.readFile(fileName);
        IndexBuffer jsonTokens   = new IndexBuffer(1024, true);
        IndexBuffer jsonElements = new IndexBuffer(1024, true);
        JsonParser jsonParser   = new JsonParser(jsonTokens, jsonElements);

        int iterations = 10000000;
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
