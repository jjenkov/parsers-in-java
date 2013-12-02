package com.jenkov.parsers.json;

import com.jenkov.parsers.core.DataCharBuffer;
import com.jenkov.parsers.core.IndexBuffer;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class does a success / error test of a set of data files found in the "data" directory.
 * The test does not verify the correct tokens are found during parsing, just that the parsing
 * does not fail (throw an exception). This test is just a quick way to check if the parser
 * can handle a given file at all.
 *
 */
public class JsonOrgExamplesTest {

    @Test
    public void testFileSet() throws IOException {
        parseFile("data/json-org/example-1.json.txt");
        parseFile("data/json-org/example-2.json.txt");
        parseFile("data/json-org/example-3.json.txt");
        parseFile("data/json-org/example-4.json.txt");
        parseFile("data/json-org/example-5.json.txt");
        parseFile("data/richard-hightower/large.json.txt");
    }

    @Test
    public void testFileSetParser2() throws IOException {
        parseFile2("data/json-org/example-1.json.txt");
        parseFile2("data/json-org/example-2.json.txt");
        parseFile2("data/json-org/example-3.json.txt");
        parseFile2("data/json-org/example-4.json.txt");
        parseFile2("data/json-org/example-5.json.txt");
        parseFile2("data/richard-hightower/large.json.txt");
    }

    private static void parseFile(String filePath) throws IOException {
        DataCharBuffer dataBuffer = new DataCharBuffer();

        dataBuffer.data = readFile(filePath);

        dataBuffer.length = dataBuffer.data.length;

        IndexBuffer tokenBuffer   = new IndexBuffer(dataBuffer.data.length, true);
        IndexBuffer elementBuffer = new IndexBuffer(dataBuffer.data.length, true);
        JsonParser parser = new JsonParser(tokenBuffer, elementBuffer);

        parser.parse(dataBuffer);
    }

    private static void parseFile2(String filePath) throws IOException {
        DataCharBuffer dataBuffer = new DataCharBuffer();

        dataBuffer.data = readFile(filePath);

        dataBuffer.length = dataBuffer.data.length;

        IndexBuffer elementBuffer = new IndexBuffer(dataBuffer.data.length, true);
        JsonParser2 parser        = new JsonParser2();

        parser.parse(dataBuffer, elementBuffer);
    }


    public static char[] readFile(String filePath) throws IOException {

        File file = new File(filePath);
        char[] chars = new char[(int) file.length()];

        FileReader reader = new FileReader(file);
        long charsRead = reader.read(chars);
        while(charsRead < file.length()) {
            charsRead += reader.read(chars);
        }

        return chars;
    }
}
