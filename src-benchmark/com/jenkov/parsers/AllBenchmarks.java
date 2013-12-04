package com.jenkov.parsers;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.jenkov.parsers.core.DataCharBuffer;
import com.jenkov.parsers.core.IndexBuffer;
import com.jenkov.parsers.json.JsonParser;
import com.jenkov.parsers.json.JsonParser2;

import java.io.CharArrayReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 */
public class AllBenchmarks {

    public static void main(String[] args) throws IOException {
        String benchmark = "JsonParser2Builder";
        if(args.length > 0) {
            benchmark = args[0];
        }
        String fileName = "data/medium.json.txt";
        if(args.length > 1) {
            fileName = args[1];
        }
        int iterations = 10 * 1000 * 1000; //10.000.000 iterations to warm up JIT and minimize one-off overheads etc.
        if(args.length > 2){
            iterations = Integer.parseInt(args[2]);
        }
        DecimalFormat format = new DecimalFormat("###,###");
        System.out.println("Benchmark : " + benchmark);
        System.out.println("File      : " + fileName);
        System.out.println("Iterations: " + format.format(iterations));

        DataCharBuffer buffer = FileUtil.readFile(fileName);

        long startTime = System.currentTimeMillis();
        if("JsonParser".equals(benchmark)) {
            runJsonParserBenchmark(buffer, iterations);
        } else if("JsonParser2".equals(benchmark)) {
            runJsonParser2Benchmark(buffer, iterations);
        } else if("JsonParserBuilder".equals(benchmark)) {
            runJsonParserBuilderBenchmark(buffer, iterations);
        } else if("JsonParser2Builder".equals(benchmark)) {
            runJsonParser2BuilderBenchmark(buffer, iterations);
        } else if("GsonStream".equals(benchmark)) {
            runGsonStreamBenchmark(buffer, iterations);
        } else if("GsonStreamBuilder".equals(benchmark)) {
            runGsonStreamBuilderBenchmark(buffer, iterations);
        } else if("GsonMap".equals(benchmark)) {
            runGsonMapBenchmark(buffer, iterations);
        } else if("GsonReflection".equals(benchmark)) {
            runGsonReflectionBenchmark(buffer, iterations);
        } else {
            System.out.println("Benchmark not recognized: " + benchmark);
            return;
        }
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("time      : " + format.format(totalTime));


    }


    private static void runJsonParserBenchmark(DataCharBuffer buffer, int iterations) {
        IndexBuffer jsonTokens   = new IndexBuffer(8192, true);
        IndexBuffer jsonElements = new IndexBuffer(8192, true);

        JsonParser jsonParser    = new JsonParser(jsonTokens, jsonElements);

        for(int i=0; i<iterations; i++) {
            jsonParser.parse(buffer);
        }

    }

    private static void runJsonParser2Benchmark(DataCharBuffer buffer, int iterations)  {
        IndexBuffer jsonElements = new IndexBuffer(8192, true);

        JsonParser2 jsonParser    = new JsonParser2();

        for(int i=0; i<iterations; i++) {
            jsonParser.parse(buffer, jsonElements);
        }

    }

    private static void runJsonParserBuilderBenchmark(DataCharBuffer buffer, int iterations) {
        IndexBuffer jsonTokens   = new IndexBuffer(8192, true);
        IndexBuffer jsonElements = new IndexBuffer(8192, true);

        JsonParser jsonParser    = new JsonParser(jsonTokens, jsonElements);

        for(int i=0; i<iterations; i++) {
            jsonParser.parse(buffer);
            JsonObject jsonObject = JsonObjectBuilder.parseJsonObject(buffer, jsonElements);
        }
    }

    private static void runJsonParser2BuilderBenchmark(DataCharBuffer buffer, int iterations)  {
        IndexBuffer jsonElements = new IndexBuffer(8192, true);

        JsonParser2 jsonParser    = new JsonParser2();

        for(int i=0; i<iterations; i++) {
            jsonParser.parse(buffer, jsonElements);
            JsonObject jsonObject = JsonObjectBuilder.parseJsonObject(buffer, jsonElements);
        }

    }


    private static final void gsonStreamParseObject(JsonReader reader) throws IOException {
        reader.beginObject();

        while (reader.hasNext()) {
            String name  = reader.nextName();

            if("key".equals(name)){
                reader.nextString();
            } else if("key2".equals(name)){
                reader.nextInt();
            } else if("key3".equals(name)){
                reader.nextBoolean();
            } else if("stringArray".equals(name)) {
                reader.beginArray();
                while(reader.hasNext()){
                    reader.nextString();
                }
                reader.endArray();
            } else if("numberArray".equals(name)) {
                reader.beginArray();
                while(reader.hasNext()){
                    reader.nextInt();
                }
                reader.endArray();
            } else if("booleanArray".equals(name)) {
                reader.beginArray();
                while(reader.hasNext()){
                    reader.nextBoolean();
                }
                reader.endArray();
            } else if("sub".equals(name)) {
                gsonStreamParseObject(reader);
            }
        }
        reader.endObject();

    }


    private static void runGsonStreamBenchmark(DataCharBuffer buffer, int iterations) throws IOException {

        for(int i=0; i<iterations; i++) {
            JsonReader reader = new JsonReader(new CharArrayReader(buffer.data, 0, buffer.length));
            gsonStreamParseObject(reader);
            reader.close();
        }
    }

    private static void runGsonStreamBuilderBenchmark(DataCharBuffer buffer, int iterations) throws IOException {

        for(int i=0; i<iterations; i++) {
            JsonReader reader = new JsonReader(new CharArrayReader(buffer.data, 0, buffer.length));
            gsonStreamBuildObject(reader);
            reader.close();
        }
    }

    private static final JsonObject gsonStreamBuildObject(JsonReader reader) throws IOException {
        JsonObject jsonObject = new JsonObject();

        reader.beginObject();

        while (reader.hasNext()) {
            String name  = reader.nextName();

            if("key".equals(name)){
                jsonObject.key = reader.nextString();
            } else if("key2".equals(name)){
                jsonObject.key2 = reader.nextInt();
            } else if("key3".equals(name)){
                jsonObject.key3 = reader.nextBoolean();
            } else if("stringArray".equals(name)) {
                List<String> elements = new ArrayList<String>();
                reader.beginArray();
                while(reader.hasNext()){
                    elements.add(reader.nextString());
                }
                reader.endArray();
                jsonObject.stringArray = elements.toArray(new String[elements.size()]);
            } else if("numberArray".equals(name)) {
                List<Integer> elements = new ArrayList<Integer>();
                reader.beginArray();
                while(reader.hasNext()){
                    elements.add(reader.nextInt());
                }
                reader.endArray();
                int[] ints = new int[elements.size()];
                for(int i=0; i<ints.length; i++) {
                    ints[i] = elements.get(i);
                }
                jsonObject.numberArray = ints;
            } else if("booleanArray".equals(name)) {
                List<Boolean> elements = new ArrayList<Boolean>();
                reader.beginArray();
                while(reader.hasNext()){
                    elements.add(reader.nextBoolean());
                }
                reader.endArray();
                boolean[] booleans = new boolean[elements.size()];
                for(int i=0; i<booleans.length; i++) {
                    booleans[i] = elements.get(i);
                }
                jsonObject.booleanArray = booleans;
            } else if("sub".equals(name)) {
                jsonObject.sub = gsonStreamBuildObject(reader);
            }
        }
        reader.endObject();

        return jsonObject;
    }

    private static void runGsonMapBenchmark(DataCharBuffer buffer, int iterations) {
        Gson gson = new Gson();

        for(int i=0; i<iterations; i++) {
            Map<String, Object> map = (Map<String, Object>) gson.fromJson (
                    new CharArrayReader( buffer.data, 0, buffer.length ), Map.class );

        }

    }

    private static void runGsonReflectionBenchmark(DataCharBuffer buffer, int iterations) {
        Gson gson = new Gson();

        for(int i=0; i<iterations; i++) {
            JsonObject jsonObject = gson.fromJson(new CharArrayReader(buffer.data, 0, buffer.length), JsonObject.class);
        }

    }





}
