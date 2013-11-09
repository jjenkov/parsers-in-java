package com.jenkov.parsers;

import com.jenkov.parsers.core.DataCharBuffer;

import java.io.FileReader;
import java.io.IOException;

/**
 */
public class FileUtil {

    public static DataCharBuffer readFile(String fileName) throws IOException {
        FileReader reader = new FileReader(fileName);
        DataCharBuffer dataCharBuffer = new DataCharBuffer(1024);
        dataCharBuffer.length = reader.read(dataCharBuffer.data);
        reader.close();
        return dataCharBuffer;
    }

}
